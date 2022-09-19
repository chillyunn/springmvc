package com.jirandata.member;

import com.jirandata.member.dtos.MemberRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "member_id",nullable = false)
    private String memberId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String region;


    @Builder
    public Member(String memberId, String name, String password, String department, String position, String region) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.department = department;
        this.position = position;
        this.region = region;
    }

    public void changeMember(MemberRequestDto requestDto) {
        this.memberId = requestDto.getMemberId();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.department = requestDto.getDepartment();
        this.position = requestDto.getPosition();
        this.region = requestDto.getRegion();
    }

    @Override
    public String toString() {
        return "Member{" + "id="+id+"  memberId="+ memberId + "  name=" + name + "   password="+ password + "   department="+department+
                "   position=" + position + "  region="+  region + "}";
    }
}
