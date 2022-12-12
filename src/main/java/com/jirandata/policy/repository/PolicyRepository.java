package com.jirandata.policy.repository;

import com.jirandata.policy.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
    @Query("select distinct p from Policy p join fetch p.groups where p.id in (:ids)")
    List<Policy> findAllByIds(@Param("ids")List<Long> ids);

}
