package com.jirandata.member.dtos;

import com.jirandata.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String memberId;
    private String name;
    private String password;
    private String department;
    private String region;
    private String position;

    @Builder
    public MemberSaveRequestDto(String memberId, String name, String password, String department, String region, String position) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.department = department;
        this.region = region;
        this.position = position;
    }
    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .password(password)
                .department(department)
                .position(position)
                .region(region)
                .build();
    }
}
