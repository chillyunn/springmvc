package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AgentApiController {
    private final AgentService agentService;

    @GetMapping("/api/agents")
    public List<AgentListResponseDto> findAll(){
        return agentService.findAll();
    }
}
