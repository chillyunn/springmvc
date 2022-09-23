package com.jirandata.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;


    //동적쿼리를 통해 페이지에 해당하는 열 조회
    public PageImpl<Member> findAllColumnsArrayPageable(MemberSearchType memberSearchType, String keyword,Pageable pageable) {
        List<Member> content = queryFactory.selectFrom(member)
                .where(search(memberSearchType,keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

    //동적쿼리를 통해 얻은 열의 갯수 조회
    public Long findCountByColumnsArrayPageable(MemberSearchType memberSearchType, String keyword) {
        return queryFactory.select(Wildcard.count).from(member)
                .where(search(memberSearchType,keyword))
                .fetch().get(0);
    }

    private BooleanExpression search(MemberSearchType memberSearchType, String keyword) {
        return !StringUtils.hasText(keyword) ? null : memberSearchType.contains(keyword);
    }
}
