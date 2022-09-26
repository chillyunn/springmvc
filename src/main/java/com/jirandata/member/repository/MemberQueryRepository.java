package com.jirandata.member.repository;

import com.jirandata.member.Member;
import com.jirandata.member.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.function.Supplier;


@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;


    //동적쿼리를 통해 페이지에 해당하는 열 조회
    public PageImpl<Member> findAllColumnsArrayPageable(MemberSearchType memberSearchType, String keyword, int columnIndex, OrderDirection orderDirection, Pageable pageable) {
        List<Member> content = queryFactory.selectFrom(member)
                .where(builderSearch(memberSearchType,keyword))
                .orderBy(order(columnIndex,orderDirection))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

    //동적쿼리를 통해 얻은 열의 갯수 조회
    public Long findCountByColumnsArrayPageable(MemberSearchType memberSearchType, String keyword) {
        return queryFactory.select(Wildcard.count).from(member)
                .where(builderSearch(memberSearchType,keyword))
                .fetch().get(0);
    }

    //첫 파라미터가 null일경우 NPE 발생
    private BooleanExpression search(MemberSearchType memberSearchType, String keyword) {
        return !StringUtils.hasText(keyword) ? null : memberSearchType.contains(keyword);
    }
    //첫 파라미터가 null이여도 정상 동작
    private BooleanBuilder builderSearch(MemberSearchType memberSearchType,String keyword){
        return nullSafeBuilder(()-> memberSearchType.contains(keyword));
    }


    // 유틸리티 클래스로 분리하여 public static 선언으로 공통사용가능
    private BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f){
        try{
            return new BooleanBuilder(f.get());
        }catch (IllegalArgumentException e){
            return new BooleanBuilder();
        }
    }

    private OrderSpecifier<String> order(int columnIndex,OrderDirection orderDirection){
       MemberOrderType memberOrderType = MemberOrderType.values()[columnIndex];
        return orderDirection.order(memberOrderType.getStringPath());
    }
}
