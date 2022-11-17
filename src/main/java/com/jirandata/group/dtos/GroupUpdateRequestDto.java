package com.jirandata.group.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupUpdateRequestDto {
    @NotBlank
    private String name;
    private String parentName;
    @NotBlank
    private String sort;

    @Override
    public String toString() {
        return "GroupUpdateRequestDto{" +
                "name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
