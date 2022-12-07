package com.jirandata.agent.repository;

import com.jirandata.agent.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByName(String name);
}
