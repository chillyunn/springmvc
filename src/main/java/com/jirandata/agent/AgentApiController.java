package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import com.jirandata.agent.dtos.AgentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AgentApiController {
    private final AgentService agentService;

    @PostMapping("/api/agent")
    public Long save(@RequestBody AgentSaveRequestDto requestDto){
        return agentService.save(requestDto);
    }
    @GetMapping("/api/agents")
    public List<AgentListResponseDto> findAll(){
        return agentService.findAll();
    }
}
