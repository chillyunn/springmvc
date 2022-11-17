package com.jirandata.group;

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
class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    void groupInsertTest() throws Exception {
        //given
        Group group1 = Group.builder()
                .name("그룹1")
                .parent(null)
                .build();
        groupRepository.save(group1);
        Group savedGroup=groupRepository.findAll().get(0);
        Group group2 = Group.builder()
                .name("그룹2")
                .parent(savedGroup)
                .build();
        //when
        groupRepository.save(group2);
        List<Group> groups = groupRepository.findAll();
        //then
        Group secondSavedGroup=groups.get(1);
        assertThat(secondSavedGroup.getName()).isEqualTo("그룹2");
        assertThat(secondSavedGroup.getParent().getId()).isEqualTo(savedGroup.getId());
    }
}