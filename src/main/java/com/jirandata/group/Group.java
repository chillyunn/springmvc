package com.jirandata.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jirandata.policy.Policy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "group_t")
public class Group {
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

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Group> childrens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Builder
    public Group(String name, Group parent, String sort) {
        this.name = name;
        this.parent = parent;
        this.sort = sort;
    }

    public void update(String name, Group parent, String sort) {
        this.name = name;
        this.parent = parent;
        this.sort = sort;
    }

    public boolean hasParent() {
        if (this.parent != null) {
            return true;
        }
        return false;
    }

    public void appendChild(Group group) {
        this.getChildrens().add(group);
    }

    public void applyPolicy(Policy policy) {
        if(this.policy != null){
            this.policy.getGroups().remove(this);
        }
        this.policy = policy;
        policy.getGroups().add(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(parent, group.parent) && Objects.equals(name, group.name) && Objects.equals(sort, group.sort) && Objects.equals(childrens, group.childrens) && Objects.equals(policy, group.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parent, name, sort, childrens, policy);
    }
}
