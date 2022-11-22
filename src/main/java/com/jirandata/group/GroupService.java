package com.jirandata.group;

import com.jirandata.group.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupQueryRepository groupQueryRepository;

    @Transactional
    public Long save(GroupSaveRequestDto requestDto){
        Group parent = groupRepository.findByName(requestDto.getParentName());
        Group child= requestDto.toEntity(parent);

        if (parent != null){
            parent.appendChild(child);
        }
        return groupRepository.save(child).getId();
    }
    @Transactional
    public Long update(Long id, GroupUpdateRequestDto requestDto){
        Group group = groupRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹입니다."));
        Group parent = groupRepository.findByName(requestDto.getParentName());
        group.update(requestDto.getName(),parent,requestDto.getSort());
        groupRepository.save(group);
        return id;
    }
    @Transactional
    public void delete(Long id){
        Group group = groupRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 그룹입니다."));
        log.info("삭제 대상 그룹 확인: {}",group.toString());
        groupRepository.delete(group);
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
