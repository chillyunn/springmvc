package com.jirandata.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AgentApiController {
    private final AgentService agentService;
}
