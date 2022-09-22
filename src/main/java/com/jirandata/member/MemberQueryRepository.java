package com.jirandata.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;


    public List<Member> findByName(String name){
        return queryFactory.selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }
    public List<Member> findAllContainsName(String name){
        return queryFactory.selectFrom(member)
                .where(member.name.contains(name))
                .fetch();
    }
    public List<Member> findByAllColumns(String name,String id){
        return queryFactory.selectFrom(member)
                .where(containsMemberId(id))
                .fetch();
    }
    private BooleanExpression containsMemberId(String id){
        if(!StringUtils.hasLength(id))
            return null;
        return member.memberId.contains(id);
    }
}
