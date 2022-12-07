package com.jirandata.policy.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyDataTableResponseDto {
    String name;
    Integer numberOfGroup;
    Integer numberOfAgent;
    String createdAt;
    String lastModifiedAt;

    @QueryProjection
    public PolicyDataTableResponseDto(String name, Integer numberOfGroup, Integer numberOfAgent, String createdAt, String lastModifiedAt) {
        this.name = name;
        this.numberOfGroup = numberOfGroup;
        this.numberOfAgent = numberOfAgent;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public String toString() {
        return "PolicyDataTableResponseDto{" +
                "name='" + name + '\'' +
                ", numberOfGroup=" + numberOfGroup +
                ", numberOfAgent=" + numberOfAgent +
                ", createdAt='" + createdAt + '\'' +
                ", lastModifiedAt='" + lastModifiedAt + '\'' +
                '}';
    }
}


