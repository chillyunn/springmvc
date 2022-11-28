package com.jirandata.agent;

import com.jirandata.agent.dtos.AgentListResponseDto;
import com.jirandata.agent.dtos.QAgentListResponseDto;
import com.jirandata.group.Group;
import com.jirandata.util.OrderDirection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jirandata.util.QueryBuildUtils.nullSafeBuilder;

@RequiredArgsConstructor
@Repository
public class AgentQueryRepository {
    private final JPAQueryFactory queryFactory;
    QAgent agent = QAgent.agent;

    public Long findAgentCountByGroup(Group parent){
        return queryFactory.select(Wildcard.count)
                .from(agent)
                .where(parentEqChildrenIn(parent))
                .fetchFirst();
    }
    public PageImpl<AgentListResponseDto> findAllAgentsByGroupPageable(Group parent, int columnIndex, OrderDirection orderDirection, Pageable pageable){
        List<AgentListResponseDto> result= queryFactory.select(new QAgentListResponseDto(agent))
                .from(agent)
                .where(parentEqChildrenIn(parent))
//                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result,pageable,result.size());
    }
    private BooleanBuilder parentEqChildrenIn(Group parent){
        if (parent ==null){
            return parentEq(null);
        }
        return parentEq(parent).or(childrenIn(parent.getChildrens()));
    }
    private BooleanBuilder parentEq(Group parent){
        return nullSafeBuilder(()->agent.group.eq(parent));
    }
    private BooleanBuilder childrenIn(List<Group> children){
        return nullSafeBuilder(()->agent.group.in(children));
    }

//    @Override
//    public OrderSpecifier<String> order(int columnIndex, OrderDirection orderDirection){
//
//    }
}
