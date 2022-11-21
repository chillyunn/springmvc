package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import com.jirandata.agent.dtos.AgentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgentService {
    private final AgentRepository agentRepository;

//    public Long save(AgentSaveRequestDto requestDto){
//        return agentRepository.save(requestDto.toEntity());
//    }

    public List<AgentListResponseDto> findAll() {
        return agentRepository.findAll()
                .stream()
                .map(AgentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
