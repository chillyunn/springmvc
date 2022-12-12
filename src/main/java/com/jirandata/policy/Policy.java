package com.jirandata.policy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jirandata.BaseTimeEntity;
import com.jirandata.agent.Agent;
import com.jirandata.group.Group;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "policy_t")
public class Policy extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String content;


    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    List<Group> groups = new ArrayList<>();
    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    List<Agent> agents = new ArrayList<>();

    @Builder
    public Policy(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(id, policy.id) && Objects.equals(name, policy.name) && Objects.equals(content, policy.content) && Objects.equals(groups, policy.groups) && Objects.equals(agents, policy.agents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, groups, agents);
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' + '}';
    }
}
