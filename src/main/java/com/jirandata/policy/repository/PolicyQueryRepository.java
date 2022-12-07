package com.jirandata.policy.repository;

import com.jirandata.agent.QAgent;
import com.jirandata.group.QGroup;
import com.jirandata.policy.Policy;
import com.jirandata.policy.QPolicy;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PolicyQueryRepository {

    private final JPAQueryFactory queryFactory;
    QPolicy policy = QPolicy.policy;
    QGroup group = QGroup.group;
    QAgent agent = QAgent.agent;

    public Long findCount(){
        return queryFactory.select(Wildcard.count).from(policy).where()
                .fetchFirst();
    }
    public PageImpl<Policy> findAll(Pageable pageable){
        List<Policy> policies = queryFactory.selectFrom(policy)
                .where()
                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(policies,pageable,policies.size());
    }
//    public PageImpl<Policy> findAll(Pageable pageable){
//        List<Policy> policies = queryFactory.selectFrom(policy)
//                .join(policy.groups)
//                //.fetchJoin()
//                .join(policy.agents)
//                //.fetchJoin()
//                .orderBy()
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(policies,pageable,policies.size());
//    }
}
