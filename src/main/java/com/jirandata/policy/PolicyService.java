package com.jirandata.policy;

import com.jirandata.agent.Agent;
import com.jirandata.agent.repository.AgentRepository;
import com.jirandata.common.dto.DataTableResponseDto;
import com.jirandata.group.Group;
import com.jirandata.group.repository.GroupRepository;
import com.jirandata.policy.dto.PolicySaveRequestDto;
import com.jirandata.policy.repository.PolicyQueryRepository;
import com.jirandata.policy.repository.PolicyRepository;
import com.jirandata.util.DatatablePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final PolicyQueryRepository policyQueryRepository;
    private final GroupRepository groupRepository;
    private final AgentRepository agentRepository;

    public void save(PolicySaveRequestDto requestDto){
        log.info("정책 대상 그룹명: {}",requestDto.getGroups());
        Policy policy = requestDto.toEntity();
        policyRepository.save(policy);
        for(String groupName:requestDto.getGroups()){
            Group group =groupRepository.findByName(groupName).orElseThrow(()->new IllegalArgumentException("존재 하지 않는 그룹 이름"));
            group.applyPolicy(policy);
        }
        for(String agentName: requestDto.getAgents()){
            Agent agent= agentRepository.findByName(agentName).orElseThrow(()->new IllegalArgumentException("존재하지 않는 에이전트 이름"));
            agent.applyPolicy(policy);
        }

    }

    @Transactional(readOnly = true)
    public DataTableResponseDto findAll(MultiValueMap<String,String> map){
        DatatablePayload datatablePayload = new DatatablePayload(map);
        Pageable pageable = datatablePayload.getPageable();
        //검색
        //페이지 수
        long total = policyQueryRepository.findCount();
        PageImpl<Policy> data = policyQueryRepository.findAll(pageable);

        return DataTableResponseDto.builder()
                .draw(datatablePayload.getDraw())
                .recordsTotal(total)
                .recordsFilterd(total)
                .data(data.getContent())
                .build();
    }

    public void delete(List<Long> ids) {
        policyRepository.deleteAllByIdInBatch(ids);
    }
    public void disapplyPolicyBeforeDelete(List<Long> ids){
        List<Policy> policies =policyRepository.findAllByIds(ids);
        log.info("삭제 대상 정책: {}",policies);
        List<Group> groups = policies.stream().map(Policy::getGroups).flatMap(List::stream).collect(Collectors.toList());
        log.info("정책 해제 대상 그룹: {}",groups);
        groups.forEach(group -> group.disapplyPolicy());
        List<Agent> agents = policies.stream().map(Policy::getAgents).flatMap(List::stream).collect(Collectors.toList());
        log.info("정책 해제 대상 에이전트: {}",agents);
        agents.forEach(agent -> agent.disapplyPolicy());
    }
    @Transactional
    public List<Policy> findAllByIds(List<Long> ids){
        return policyRepository.findAllByIds(ids);
    }
}
