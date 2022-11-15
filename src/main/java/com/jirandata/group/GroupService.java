package com.jirandata.group;

import com.jirandata.group.dtos.GroupFindByNameRequestDto;
import com.jirandata.group.dtos.GroupListResponseDto;
import com.jirandata.group.dtos.GroupResponseDto;
import com.jirandata.group.dtos.GroupSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupQueryRepository groupQueryRepository;

    @Transactional
    public Long save(GroupSaveRequestDto requestDto){
        Group parent = groupRepository.findByName(requestDto.getParentName());
        return groupRepository.save(requestDto.toEntity(parent)).getId();
    }
    @Transactional(readOnly = true)
    public List<GroupResponseDto> findAll(){
        return groupRepository.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Group findGroupByName(GroupFindByNameRequestDto requestDto){
        return groupRepository.findByName(requestDto.getName());
    }
    @Transactional(readOnly = true)
    public List<GroupListResponseDto> findAllGroups(){
        return groupQueryRepository.getGroupList();
    }
}
