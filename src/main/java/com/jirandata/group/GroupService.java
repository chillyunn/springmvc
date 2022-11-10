package com.jirandata.group;

import com.jirandata.group.dtos.GroupFindByNameRequestDto;
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

    @Transactional
    public Long save(GroupSaveRequestDto requestDto){
        return groupRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional(readOnly = true)
    public List<GroupResponseDto> findAll(){
        return groupRepository.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public GroupResponseDto findGroupByName(GroupFindByNameRequestDto requestDto){
        return groupRepository.findByName(requestDto.getName());
    }
}
