package com.jirandata.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

import static com.jirandata.member.QMember.member;
// 검색 카테고리 구현 및 카테고리에 따른 검색기능 위임
public enum MemberSearchType {
    ALL(value -> member.memberId.contains(value)
            .or(member.name.contains(value))
            .or(member.department.contains(value))
            .or(member.position.contains(value))
            .or(member.region.contains(value))),
    MEMBERID(value -> member.memberId.contains(value)),
    NAME(value -> member.name.contains(value)),
    DEPARTMENT(value -> member.department.contains(value)),
    POSITION(value -> member.position.contains(value)),
    REGION(value -> member.region.contains(value));

    private Function<String, BooleanExpression> expression;

    MemberSearchType(Function<String,BooleanExpression> expression){this.expression = expression;}

    public BooleanExpression contains(String value){
        return expression.apply(value);
    }

    @JsonCreator
    public static MemberSearchType from(String s){
        return MemberSearchType.valueOf(s.toUpperCase());
    }
}
