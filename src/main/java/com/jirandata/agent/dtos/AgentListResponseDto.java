package com.jirandata.agent.dtos;

import com.jirandata.agent.Agent;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentListResponseDto {
    private Long id;
    private String name;
    private String groupName;
    private String ip;
    private String mac;
    private String createdAt;
    private String modifiedAt;

    @QueryProjection
    public AgentListResponseDto(Agent agent) {
        this.id = agent.getId();
        this.name = agent.getName();
        this.groupName = agent.getGroup().getName();
        this.ip = agent.getIp();
        this.mac = agent.getMac();
        this.createdAt = agent.getCreatedAt();
        this.modifiedAt = agent.getModifiedAt();
    }
}
