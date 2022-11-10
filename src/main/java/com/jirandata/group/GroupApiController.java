package com.jirandata.group;

import com.jirandata.group.dtos.GroupFindByNameRequestDto;
import com.jirandata.group.dtos.GroupResponseDto;
import com.jirandata.group.dtos.GroupSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupApiController {
    private final GroupService groupService;

    @PostMapping("/api/group")
    public Long save(@RequestBody GroupSaveRequestDto requestDto){
        log.info(requestDto.toString());
        return groupService.save(requestDto);
    }
    @GetMapping("/api/groups")
    public List<GroupResponseDto> findAll(){
        return groupService.findAll();
    }
    @GetMapping("/api/group")
    public GroupResponseDto findByName(GroupFindByNameRequestDto requestDto){
        return groupService.findGroupByName(requestDto);
    }
}
