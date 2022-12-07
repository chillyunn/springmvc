package com.jirandata.policy.repository;

import com.jirandata.agent.Agent;
import com.jirandata.group.Group;
import com.jirandata.policy.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class PolicyRepositoryTest {
    @Autowired
    PolicyRepository policyRepository;

    @Transactional
    @Test
    public void jpql_joinfetch_test() {
        //given
        List<Long> ids = Arrays.asList(5L, 6L, 7L);
        //when
        List<Policy> result= policyRepository.findAllByIds(ids);
        //then
        List<Group> groups= result.get(0).getGroups();
        List<Agent> agents = result.get(0).getAgents();
        System.out.println(groups);
        System.out.println(agents);
        assertThat(result.size()).isEqualTo(3);
    }
}