package com.jirandata.group.dtos;

import com.jirandata.group.Group;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupSaveRequestDto {
    @NotBlank
    private String name;
    private String parentName;
    @NotBlank
    private String sort;

    @Builder
    public GroupSaveRequestDto(Group group) {
        this.name = group.getName();
        this.parentName = getParentName();
        this.sort = group.getSort();
    }
    public Group toEntity(Group parent){
        return Group.builder()
                .name(name)
                .parent(parent)
                .sort(sort)
                .build();
    }

    @Override
    public String toString() {
        return "name: "+this.name+ " parentName: "+parentName+" sort: "+ this.sort;
    }
}
