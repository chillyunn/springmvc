package com.jirandata.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class MemberQueryRepositoryTest {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Test
    void querydsl_findByNameTest(){
        String name="한기윤";

        List<Member> result = memberQueryRepository.findByName(name);

        assertThat(result.size(),is(1));
        assertThat(result.get(0).getName(),is(name));
    }
    @Test
    void 파라미터가_포함된_행찾기(){
        String parameter="1";

        List<Member> result = memberQueryRepository.findAllContainsName(parameter);

        result.stream().forEach(m-> System.out.println(m.toString()));
    }

}