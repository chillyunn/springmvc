package com.jirandata.agent.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentUpdateRequestDto {

    @NotBlank
    private String name;
    private String groupName;
    @NotBlank
    private String ip;
    @NotBlank
    private String mac;


    @Override
    public String toString() {
        return "AgentUpdateRequestDto{" +
                "name='" + name + '\'' +
                ", groupName='" + groupName + '\'' +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
