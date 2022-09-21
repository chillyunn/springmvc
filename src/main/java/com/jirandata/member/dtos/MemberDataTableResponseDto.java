package com.jirandata.member.dtos;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class MemberDataTableResponseDto {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List data;


    public List getData(){
        if(CollectionUtils.isEmpty(data)){
            data = new ArrayList();
        }
        return data;
    }
    @Builder
    public MemberDataTableResponseDto(int draw, int recordsTotal, int recordsFilterd, List data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFilterd;
        this.data = data;
    }
}
