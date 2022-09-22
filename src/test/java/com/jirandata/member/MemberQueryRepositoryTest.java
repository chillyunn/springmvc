package com.jirandata.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Test
    void 테이블에서_파라미터찾기(){
        String parameter="1";
        List<Member> result = memberQueryRepository.findByAllColumns(parameter,parameter,parameter,parameter,parameter);
        result.stream().forEach(m-> System.out.println(m.toString()));
    }
    @Test
    void 테이블에서_파라미터찾기_거주지(){
        String[] parameters={"","","","","서울"};
        List<Member> result = memberQueryRepository.findByAllColumnsArray(parameters);
        result.stream().forEach(m-> System.out.println(m.toString()));
    }
    @Test
    void 테이블에서_파라미터찾기_빈검색(){
        String[] parameters={"","","","",""};
        List<Member> result = memberQueryRepository.findByAllColumnsArray(parameters);
        result.stream().forEach(m-> System.out.println(m.toString()));
    }
    @Test
    void 테이블에서_파라미터찾기_전체(){
        String[] parameters={"1","1","1","1","1"};
        List<Member> result = memberQueryRepository.findByAllColumnsArray(parameters);
        result.stream().forEach(m-> System.out.println(m.toString()));
    }
    @Test
    void 테이블에서_파라미터찾기_페이징(){
        String[] parameters={"","","","",""};
        Pageable pageable = PageRequest.of(1,5);
        PageImpl<Member> result = memberQueryRepository.findAllColumnsArrayPageable(parameters,pageable);
        System.out.println(result.getNumber());
        System.out.println(result.getTotalPages());
        System.out.println(result.getContent().size());
        System.out.println(result.getContent());
    }
    @Test
    void 테이블에서_파라미터찾기_Count(){
        String[] parameters={"","","","","서울"};
        System.out.println(memberQueryRepository.findCountByColumnsArrayPageable(parameters));
    }
}