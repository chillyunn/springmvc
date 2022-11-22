package com.jirandata.agent.dtos;

import com.jirandata.agent.Agent;
import com.jirandata.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgentSaveRequestDto {
    private String name;
    private String groupName;
    private String ip;
    private String mac;

    @Builder
    public AgentSaveRequestDto(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public Agent toEntity(Group group){
        return Agent.builder()
                .name(name)
                .group(group)
                .ip(ip)
                .mac(mac)
                .build();
    }
}
