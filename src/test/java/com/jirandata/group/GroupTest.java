package com.jirandata.group;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class GroupTest {
//    @Test
//    public void hasAgentTest() throws Exception {
//        //given
//        Group group = Group.builder()
//                .name("그룹1").build();
//        Agent agent = Agent.builder()
//                .name("에이전트1")
//                .ip("0.0.0.0")
//                .mac("11.22.33.44.55.66")
//                .build();
//        //when
//        agent.changeGroup(group);
//        //then
//        assertThat(group.getAgents().get(0).getName()).isEqualTo(agent.getName());
//    }

}