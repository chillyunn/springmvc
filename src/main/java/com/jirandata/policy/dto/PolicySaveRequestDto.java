package com.jirandata.policy.dto;

import com.jirandata.policy.Policy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicySaveRequestDto {
    private String name;
    private String content;
    private List<String> groups;
    private List<String> agents;

    public Policy toEntity(){
        return Policy.builder()
                .name(name)
                .content(content)
                .build();
    }

    @Override
    public String toString() {
        return "PolicySaveRequestDto{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", groups=" + groups +
                ", agents=" + agents +
                '}';
    }
}
