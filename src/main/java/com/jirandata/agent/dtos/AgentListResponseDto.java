package com.jirandata.agent.dtos;

import com.jirandata.agent.Agent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentListResponseDto {
    private String name;
    private String groupName;
    private String ip;
    private String mac;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifedDateTime;

    public AgentListResponseDto(Agent agent) {
        this.name = agent.getName();
        this.groupName = agent.getGroup().getName();
        this.ip = agent.getIp();
        this.mac = agent.getMac();
        this.createdDateTime = agent.getCreatedDateTime();
        this.lastModifedDateTime = agent.getLastModifiedDateTime();
    }
}
