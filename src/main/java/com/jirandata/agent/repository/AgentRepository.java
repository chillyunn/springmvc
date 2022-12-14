package com.jirandata.agent.repository;

import com.jirandata.agent.Agent;
import com.jirandata.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByName(String name);
    @Query("select a from Agent a where a.group = :group")
    List<Agent> findByGroup(@Param("group") Group group);
    List<Agent> findByGroupId(@Param("id")Long id);
}
