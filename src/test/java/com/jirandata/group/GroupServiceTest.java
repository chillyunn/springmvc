package com.jirandata.group;

import com.jirandata.group.dtos.GroupResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @Mock
    GroupRepository groupRepository;
    @InjectMocks
    GroupService groupService;

    @Test
    public void groupInsertTest() throws Exception {
        //given
        Group group1 = Group.builder().name("그룹1").sort("1234").build();
        Group group2 = Group.builder().name("그룹1").parent(group1).sort("1111").build();

        Mockito.when(groupRepository.findAll())
                .thenReturn(Arrays.asList(group1,group2));
        //when
        List<GroupResponseDto> result = groupService.findAll();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result).extracting("name","parentCode","sort")
                .contains(tuple("그룹1",null,"1234"),tuple("그룹2",group2,"1111"));

        Mockito.verify(groupRepository).findAll();
    }


}