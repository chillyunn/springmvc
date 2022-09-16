package com.jirandata.member;

import com.jirandata.member.dtos.MemberSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveTest() {
        Member member = Member.builder()
                .memberId("1")
                .name("2")
                .password("3")
                .position("4")
                .region("5")
                .department("6")
                .build();

        Member member1 = memberRepository.save(member);

        assertEquals("1",member1.getMemberId());
    }

}