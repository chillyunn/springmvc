package com.jirandata.util;

import com.querydsl.core.types.OrderSpecifier;

public interface Order {
    OrderSpecifier<String> order(int columnIndex, OrderDirection orderDirection);
}
