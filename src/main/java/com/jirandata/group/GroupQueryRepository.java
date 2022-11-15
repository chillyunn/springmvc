package com.jirandata.group;

import com.jirandata.group.dtos.GroupListResponseDto;
import com.jirandata.group.dtos.QGroupListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroupQueryRepository {
    private final JPAQueryFactory queryFactory;
    QGroup group = QGroup.group;

    public List<GroupListResponseDto> getGroupList(){
        return queryFactory.select(new QGroupListResponseDto(group.id,group.name,group.parent.id))
                .from(group)
                .orderBy(group.sort.asc())
                .fetch();
    }
}
