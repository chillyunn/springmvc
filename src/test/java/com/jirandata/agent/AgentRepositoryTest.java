package com.jirandata.agent;

import com.jirandata.agent.repository.AgentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class AgentRepositoryTest {

    @Autowired
    AgentRepository agentRepository;

    @Test
    public void agentInsertTest() throws Exception {
        //given
        Agent agent = Agent.builder()
                .name("에이전트1")
                .ip("0.0.0.0")
                .mac("11.22.33.44.55.66")
                .build();
        agentRepository.save(agent);
        //when
        List<Agent> agents = agentRepository.findAll();
        //then
        Agent result = agents.get(0);
        assertThat(result.getName()).isEqualTo("에이전트1");
    }
}