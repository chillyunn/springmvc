package com.jirandata.group;

import com.jirandata.group.dtos.GroupFindByNameRequestDto;
import com.jirandata.group.dtos.GroupListResponseDto;
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
    @GetMapping("/api/group")
    public Group findByName(GroupFindByNameRequestDto requestDto){
        return groupService.findGroupByName(requestDto);
    }
    @GetMapping("api/groups")
    public List<GroupListResponseDto> findAllGroups(){
        return groupService.findAllGroups();
    }
}
