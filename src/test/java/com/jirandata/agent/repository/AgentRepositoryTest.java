package com.jirandata.agent.repository;

import com.jirandata.agent.Agent;
import com.jirandata.group.Group;
import com.jirandata.group.repository.GroupRepository;
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
    @Autowired
    GroupRepository groupRepository;
    @Test
    public void Group으로_Agent찾기() throws Exception {
        //given
        Group group = groupRepository.findById(110L).orElseThrow(()->new IllegalArgumentException("잘못된 아이디"));
        //when
        List<Agent> agents = agentRepository.findByGroup(group);
        //then
        assertThat(agents.get(0).getName()).isEqualTo("에이전트13");
    }
    @Test
    public void groupId로_Agent찾기() throws Exception {
        //given
        Long id = 117L;
        //when
        List<Agent> agents = agentRepository.findByGroupId(id);
        //then
        assertThat(agents.get(0).getName()).isEqualTo("에이전트1234");
    }
}