package com.jirandata.group;

import com.jirandata.group.dtos.GroupListResponseDto;
import com.jirandata.group.repository.GroupQueryRepository;
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
class GroupQueryRepositoryTest {

    @Autowired
    private GroupQueryRepository groupQueryRepository;
    @Test
    public void groupSelectProjectionTest() throws Exception {
        //given

        //when
        List<GroupListResponseDto> dtos = groupQueryRepository.getGroupList();
        //then
        assertThat(dtos).hasSize(5);
        dtos.stream().forEach(System.out::println);
    }

}