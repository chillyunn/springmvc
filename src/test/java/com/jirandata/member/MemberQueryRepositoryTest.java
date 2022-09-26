package com.jirandata.member;

import com.jirandata.member.repository.MemberQueryRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
        (locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class MemberQueryRepositoryTest {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

}