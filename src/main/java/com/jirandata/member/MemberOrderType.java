package com.jirandata.member;

import com.querydsl.core.types.dsl.StringPath;
import static com.jirandata.member.QMember.member;
public enum MemberOrderType {
    MEMBERID(member.memberId),
    NAME(member.name),
    DEPARTMENT(member.department),
    POSITION(member.position),
    REGION(member.region);

    private StringPath stringPath;

    MemberOrderType(StringPath stringPath) {
        this.stringPath= stringPath;
    }

    public StringPath getStringPath() {
        return stringPath;
    }
}
