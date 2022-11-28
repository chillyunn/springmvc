package com.jirandata.common.dto;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class DataTableResponseDto {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List data;


    public List getData(){
        if(CollectionUtils.isEmpty(data)){
            data = new ArrayList();
        }
        return data;
    }
    @Builder
    public DataTableResponseDto(int draw, long recordsTotal, long recordsFilterd, List data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFilterd;
        this.data = data;
    }
}
