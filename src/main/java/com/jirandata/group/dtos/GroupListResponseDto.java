package com.jirandata.group.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jirandata.group.QGroup;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupListResponseDto {
    private Long id;
    @JsonProperty("text")
    private String name;
    @JsonProperty("parent")
    private Long parentId;

    private String sort;
    @QueryProjection
    public GroupListResponseDto(Long id, String name, Long parentId,String sort) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.sort= sort;
    }

    @Override
    public String toString() {
        return "id: "+id+" name: "+name+" parentId: "+ parentId + " sort: "+ sort;
    }
}
