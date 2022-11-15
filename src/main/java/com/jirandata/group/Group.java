package com.jirandata.group;

import com.jirandata.BaseTimeEntity;
import com.jirandata.agent.Agent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "group_t")
public class Group extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_code")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code")
    private Group parent;
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String sort;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    List<Group> childrens = new ArrayList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    List<Agent> agents = new ArrayList<>();

    @Builder
    public Group(String name, Group parent, String sort) {
        this.name = name;
        this.parent = parent;
        this.sort=sort;
    }

    public void checkAgent(Agent agent){
        if(!this.hasAgent(agent)){
            this.addAgent(agent);
        }
    }
    public void addAgent(Agent agent) {
        this.agents.add(agent);
        if (agent.getGroup() != this) {
            agent.changeGroup(this);
        }
    }
    public void removeAgent(Agent agent) {
        this.agents.remove(agent);
    }
    public boolean hasAgent(Agent agent){
        return this.agents.contains(agent) ? true : false;
    }

}
