package com.jirandata.group;

import com.jirandata.agent.Agent;
import com.jirandata.agent.repository.AgentRepository;
import com.jirandata.group.dtos.*;
import com.jirandata.group.repository.GroupQueryRepository;
import com.jirandata.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final AgentRepository agentRepository;
    private final GroupQueryRepository groupQueryRepository;

    @Transactional
    public Long save(GroupSaveRequestDto requestDto){
        Group parent = groupRepository.findByName(requestDto.getParentName()).orElse(null);
        Group child= requestDto.toEntity(parent);

        appendChildRecursion(parent,child);
        return groupRepository.save(child).getId();
    }
    @Transactional
    public Long update(Long id, GroupUpdateRequestDto requestDto){
        Group group = groupRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹입니다."));
        Group parent = groupRepository.findByName(requestDto.getParentName()).orElse(null);
        group.update(requestDto.getName(),parent,requestDto.getSort());
        groupRepository.save(group);
        return id;
    }
    @Transactional
    public void delete(Long id){
        Group defaultGroup = groupRepository.findByName("그룹미지정").orElseThrow(()->new IllegalStateException("그룹 미지정이 미존재"));
        log.info("삭제 대상 그룹 ID: {}",id);
        List<Agent> agents = agentRepository.findByGroupId(id);
        for (Agent agent:agents){
            agent.transfer(defaultGroup);
        }
        groupRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<GroupResponseDto> findAll(){
        return groupRepository.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Group findGroupByName(GroupFindByNameRequestDto requestDto){
        return groupRepository.findByName(requestDto.getName()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹이름"));
    }
    @Transactional(readOnly = true)
    public List<GroupListResponseDto> findAllGroups(){
        return groupQueryRepository.getGroupList();
    }

    @Transactional
    void appendChildRecursion(Group group,Group child){
        if(group != null && group.hasParent()){
            group.getParent().appendChild(child);
            appendChildRecursion(group.getParent(),child);
        }
    }
}
