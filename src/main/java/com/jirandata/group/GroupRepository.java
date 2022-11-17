package com.jirandata.group;

import com.jirandata.group.dtos.GroupResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Group findByName(String name);
}
