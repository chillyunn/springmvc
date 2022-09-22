package com.jirandata.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    //동적쿼리를 통해 해당하는 전체 열 조회
    //BooleanExpression을 사용할 때 첫번째 조건이 null이면 NPE 발생한다.
    public List<Member> findByAllColumns(String memberId,String name,String department,String position,String region){
        return queryFactory.selectFrom(member)
                .where(containsMemberId(memberId)
                        .or(containsName(name))
                        .or(containsDepartment(department))
                        .or(containsPosition(position))
                        .or(containsRegion(region)))
                .fetch();
    }
    //동적쿼리를 통해 해당하는 전체 열 조회
    public List<Member> findByAllColumnsArray(String[] parameters){
        return queryFactory.selectFrom(member)
                .where(searchConditions(parameters))
                .fetch();
    }
    //동적쿼리를 통해 페이지에 해당하는 열 조회
    public PageImpl<Member> findAllColumnsArrayPageable(String[] parameters, Pageable pageable){
        List<Member> content = queryFactory.selectFrom(member)
                .where(searchConditions(parameters))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content,pageable,content.size());
    }
    //동적쿼리를 통해 얻은 열의 갯수 조회
    public Long findCountByColumnsArrayPageable(String[] parameters){
        return queryFactory.select(Wildcard.count).from(member)
                .where(searchConditions(parameters))
                .fetch().get(0);
    }
    private BooleanExpression containsMemberId(String id){
        if(!StringUtils.hasText(id))
            return null;
        return member.memberId.contains(id);
    }
    private BooleanExpression containsName(String name){
        if(!StringUtils.hasText(name))
            return null;
        return member.name.contains(name);
    }
    private BooleanExpression containsDepartment(String department){
        if(!StringUtils.hasText(department))
            return null;
        return member.department.contains(department);
    }
    private BooleanExpression containsPosition(String position){
        if(!StringUtils.hasText(position))
            return null;
        return member.position.contains(position);
    }
    private BooleanExpression containsRegion(String region){
        if(!StringUtils.hasText(region))
            return null;
        return member.region.contains(region);
    }
private BooleanBuilder searchConditions(String[] searchParams){
        BooleanBuilder builder = new BooleanBuilder();

        builder.or(containsMemberId(searchParams[0]));
        builder.or(containsName(searchParams[1]));
        builder.or(containsDepartment(searchParams[2]));
        builder.or(containsPosition(searchParams[3]));
        builder.or(containsRegion(searchParams[4]));

        return builder;
}
}
