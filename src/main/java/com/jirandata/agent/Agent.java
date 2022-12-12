package com.jirandata.agent;

import com.jirandata.BaseTimeEntity;
import com.jirandata.group.Group;
import com.jirandata.policy.Policy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "agent_t")
@BatchSize(size = 1000)
@Entity
public class Agent extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String mac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_code")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Builder
    public Agent(String name, String ip, String mac, Group group) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
        this.group = group;
    }

    public void update(String name, Group group, String ip, String mac) {
        this.name = name;
        this.group = group;
        this.ip = ip;
        this.mac = mac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id) && Objects.equals(name, agent.name) && Objects.equals(ip, agent.ip) && Objects.equals(mac, agent.mac) && Objects.equals(group, agent.group) && Objects.equals(policy, agent.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ip, mac, group, policy);
    }
    public void applyPolicy(Policy policy){
        if(this.policy != null){
            this.policy.getAgents().remove(this);
        }
        this.policy=policy;
        policy.getAgents().add(this);
    }
    public void disapplyPolicy(){
        this.policy.getAgents().remove(this);
        this.policy=null;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
