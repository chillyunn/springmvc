package com.jirandata.group.repository;

import com.jirandata.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findByName(String name);
//    @Query("select g from Group g join fetch g.policy where ")
//    List<Group> findAllByIds(@Param("ids")List<Long> ids);
}
