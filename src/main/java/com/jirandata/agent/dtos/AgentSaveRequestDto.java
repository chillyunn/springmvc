package com.jirandata.agent.dtos;

import com.jirandata.agent.Agent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgentSaveRequestDto {
    private String name;
    private String ip;
    private String mac;

    @Builder
    public AgentSaveRequestDto(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public Agent toEntity(String name, String ip, String mac){
        return Agent.builder()
                .name(name)
                .ip(ip)
                .mac(mac)
                .build();
    }
}
