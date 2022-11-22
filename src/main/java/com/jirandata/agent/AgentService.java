package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import com.jirandata.agent.dtos.AgentSaveRequestDto;
import com.jirandata.group.Group;
import com.jirandata.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final GroupRepository groupRepository;
    @Transactional
    public Long save(AgentSaveRequestDto requestDto){
        Group group = groupRepository.findByName(requestDto.getGroupName());
        Agent agent = requestDto.toEntity(group);
        return agentRepository.save(agent).getId();
    }

    @Transactional(readOnly = true)
    public List<AgentListResponseDto> findAll() {
        return agentRepository.findAll()
                .stream()
                .map(AgentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
