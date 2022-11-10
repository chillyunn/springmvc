package com.jirandata.group.dtos;

import com.jirandata.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupSaveRequestDto {
    private String name;
    private Group parent;
    private String sort;

    @Builder
    public GroupSaveRequestDto(String name, Group parent, String sort) {
        this.name = name;
        this.parent = parent;
        this.sort = sort;
    }
    public Group toEntity(){
        return Group.builder()
                .name(name)
                .parent(parent)
                .sort(sort)
                .build();
    }

    @Override
    public String toString() {
        return "name: "+this.name+" sort: "+ this.sort;
    }
}
