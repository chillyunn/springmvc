package com.jirandata.member.repository;

import com.jirandata.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query(value = "select m.region AS region ,count(m.region) AS count from Member As m group by m.region")
    public List<RegionCount> countMemberByRegionInterface();

}
