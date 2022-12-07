package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import com.jirandata.agent.dtos.AgentSaveRequestDto;
import com.jirandata.agent.dtos.AgentUpdateRequestDto;
import com.jirandata.agent.repository.AgentQueryRepository;
import com.jirandata.agent.repository.AgentRepository;
import com.jirandata.common.dto.DataTableResponseDto;
import com.jirandata.group.Group;
import com.jirandata.group.repository.GroupRepository;
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
@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentQueryRepository agentQueryRepository;
    private final GroupRepository groupRepository;
    @Transactional
    public Long save(AgentSaveRequestDto requestDto){
        Group group = groupRepository.findByName(requestDto.getGroupName()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹이름"));
        Agent agent = requestDto.toEntity(group);

        return agentRepository.save(agent).getId();
    }
    @Transactional(readOnly = true)
    public DataTableResponseDto findAllAgentsByGroup(MultiValueMap<String,String>map){
        Group group= groupRepository.findByName(map.get("group").get(0)).orElse(null);
        DatatablePayload datatablePayload = new DatatablePayload(map);
        Pageable pageable = datatablePayload.getPageable();
        //검색
        //페이지 수
        long total = agentQueryRepository.findAgentCountByGroup(group);
        PageImpl<AgentListResponseDto> data = agentQueryRepository.findAllAgentsByGroupPageable(group, datatablePayload.getColumnIndex(), datatablePayload.getOrderDirection(),pageable);


        return DataTableResponseDto.builder()
                .draw(datatablePayload.getDraw())
                .recordsTotal(total)
                .recordsFilterd(total)
                .data(data.getContent())
                .build();
    }
    @Transactional(readOnly = true)
    public List<AgentListResponseDto> findAll() {
        return agentRepository.findAll()
                .stream()
                .map(AgentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, AgentUpdateRequestDto requestDto) {
        Agent agent= agentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 id"));
        Group group = groupRepository.findByName(requestDto.getGroupName()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 이름"));
        agent.update(requestDto.getName(),group,requestDto.getIp(),requestDto.getMac());
        return id;
    }
    @Transactional
    public void delete(Long id){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 id"));
        log.info("에이전트 삭제 요청: {}",agent);
        agentRepository.delete(agent);
    }
}
