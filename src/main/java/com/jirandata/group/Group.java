package com.jirandata.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Group> childrens = new ArrayList<>();

    @Builder
    public Group(String name, Group parent, String sort) {
        this.name = name;
        this.parent = parent;
        this.sort=sort;
    }
    public void update(String name, Group parent, String sort){
        this.name=name;
        this.parent=parent;
        this.sort=sort;
    }
    public boolean hasParent(){
        if (this.parent != null){
            return true;
        }
            return false;
    }
    public void appendChild(Group group){
        this.getChildrens().add(group);
    }
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
