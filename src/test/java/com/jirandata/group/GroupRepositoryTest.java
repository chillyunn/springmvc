package com.jirandata.group;

import com.jirandata.group.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Test
    void 일대다속성이_영속성컨텍스트에_저장되는가(){
        Group group = groupRepository.findByName("부모1").orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹이름"));
        System.out.println(group.toString());
        group.getChildrens().forEach(System.out::println);
    }
    @Transactional
    @Test
    void 그룹에서_자식을_조회할수있는가(){
        Group group1 = Group.builder()
                .name("부모2")
                .parent(null)
                .sort("1234")
                .build();
        Group group2 = Group.builder()
                .name("자식2")
                .parent(group1)
                .sort("1234")
                .build();
        groupRepository.save(group1);
        group1.appendChild(group2);
        groupRepository.save(group2);
        assertThat(group1.getChildrens().size()).isEqualTo(1);
    }
}