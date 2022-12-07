package com.jirandata.policy.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyListResponseDto {
    private Long id;
    private String name;
    private String content;
    private Integer groupCount;
    private Integer agentCount;
}
