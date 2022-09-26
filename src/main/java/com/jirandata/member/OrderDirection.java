package com.jirandata.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.StringPath;

import java.util.function.Function;

public enum OrderDirection {
    ASC(value-> value.asc()),
    DESC(value-> value.desc());

    private Function<StringPath, OrderSpecifier<String>> expression;

    OrderDirection(Function<StringPath, OrderSpecifier<String>> expression) {
        this.expression = expression;
    }
    public OrderSpecifier<String> order(StringPath stringPath){
        return expression.apply(stringPath);
    }

    @JsonCreator
    public static OrderDirection from(String s){
        return OrderDirection.valueOf(s.toUpperCase());
    }
}
