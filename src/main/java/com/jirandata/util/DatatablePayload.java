package com.jirandata.util;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

@Getter
public class DatatablePayload {
    private int draw;
    private int start;
    private int length;
    private String searchType;
    private String searchValue;
    private int columnIndex;
    private OrderDirection orderDirection;

    public DatatablePayload(MultiValueMap<String,String> map) {
        this.draw =Integer.parseInt(map.get("draw").get(0));
        this.start = Integer.parseInt(map.get("start").get(0));
        this.length = Integer.parseInt(map.get("length").get(0));
        this.searchType= map.get("searchType").get(0);
        this.searchValue= map.get("searchValue").get(0);
        this.columnIndex = Integer.parseInt(map.get("order[0][column]").get(0));
        this.orderDirection = OrderDirection.from(map.get("order[0][dir]").get(0));
    }
    public Pageable getPageable(){
        int page = getPage();
        return PageRequest.of(page,length);
    }
    private int getPage() {
        return start / length;
    }
}