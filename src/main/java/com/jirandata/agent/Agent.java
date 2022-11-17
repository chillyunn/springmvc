package com.jirandata.agent;

import com.jirandata.BaseTimeEntity;
import com.jirandata.group.Group;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "agent_t")
@Entity
public class Agent extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String mac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_code")
    private Group group;

    @Builder
    public Agent(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public void changeGroup(Group group){
        if(this.group != null){
            this.group.removeAgent(this);
        }
        this.group = group;
        group.checkAgent(this);
    }
}
