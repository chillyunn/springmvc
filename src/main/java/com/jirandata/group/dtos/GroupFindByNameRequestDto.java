package com.jirandata.group.dtos;

import com.jirandata.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupFindByNameRequestDto {
    private String name;

    public GroupFindByNameRequestDto(Group group) {
        this.name = group.getName();
    }
}
