package com.jirandata.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
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
    private String position;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String department;

    @Builder
    public Member(String memberId, String name, String password, String position, String region, String department) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.position = position;
        this.region = region;
        this.department = department;
    }
}
