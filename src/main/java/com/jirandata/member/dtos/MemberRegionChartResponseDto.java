package com.jirandata.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegionChartResponseDto {
    private List<String> regionList;
    private List<Long> countList;
}
