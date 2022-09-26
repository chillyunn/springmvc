package com.jirandata.member;









import com.jirandata.member.repository.MemberOrderType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberOrderTypeTest {

    @Test
    void enumTest(){
        assertThat(MemberOrderType.NAME.ordinal()).isEqualTo(1);
    }

}