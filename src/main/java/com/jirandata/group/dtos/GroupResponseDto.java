package com.jirandata.group.dtos;

import com.jirandata.group.Group;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupResponseDto {
    private Long id;
    private String name;
    private Group parent;
    private String sort;

    public GroupResponseDto(Group group){
        this.id=group.getId();
        this.name=group.getName();
        this.parent=group.getParent();
        this.sort = group.getSort();
    }
}
