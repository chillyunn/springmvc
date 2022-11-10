package com.jirandata.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentService {
    private final AgentRepository agentRepository;
}
