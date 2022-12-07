package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentSaveRequestDto;
import com.jirandata.agent.dtos.AgentUpdateRequestDto;
import com.jirandata.common.dto.DataTableResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AgentApiController {
    private final AgentService agentService;

    @PostMapping("/api/agent")
    public Long save(@RequestBody AgentSaveRequestDto requestDto){
        log.info("agent 생성 요청 {}",requestDto.toString());
        return agentService.save(requestDto);
    }
    @PostMapping("/api/agents")
    public DataTableResponseDto findAllAgentsByGroup(@RequestBody MultiValueMap<String,String> map){
        log.info("agent 조회 요청");
        return agentService.findAllAgentsByGroup(map);
    }
    @PutMapping("/api/agent/{id}")
    public Long update(@PathVariable Long id, @RequestBody AgentUpdateRequestDto requestDto){
        log.info("에이전트 수정 요청: {}",requestDto.toString());
        return agentService.update(id,requestDto);
    }
    @DeleteMapping("/api/agent/{id}")
    public void delete(@PathVariable Long id){
        agentService.delete(id);
    }
}
