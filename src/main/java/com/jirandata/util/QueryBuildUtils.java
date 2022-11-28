package com.jirandata.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Supplier;

public final class QueryBuildUtils {
    // 유틸리티 클래스로 분리하여 public static 선언으로 공통사용가능
    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f){
        try{
            return new BooleanBuilder(f.get());
        }catch (IllegalArgumentException e){
            return new BooleanBuilder();
        }
    }

}
