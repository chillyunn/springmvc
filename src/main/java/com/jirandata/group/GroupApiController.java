package com.jirandata.group;

import com.jirandata.group.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupApiController {
    private final GroupService groupService;

    @PostMapping("/api/group")
    public Long save(@Validated @RequestBody GroupSaveRequestDto requestDto){
        log.info("그룹 생성 요청: {}",requestDto.toString());
        return groupService.save(requestDto);
    }
    @GetMapping("/api/group")
    public Group findByName(GroupFindByNameRequestDto requestDto){
        return groupService.findGroupByName(requestDto);
    }
    @PutMapping("/api/group/{id}")
    public Long update(@PathVariable Long id, @RequestBody GroupUpdateRequestDto requestDto){
        log.info("그룹 수정 요청: {}",requestDto.toString());
        return groupService.update(id,requestDto);
    }
    @DeleteMapping("/api/group/{id}")
    public void delete(@PathVariable Long id){
        log.info("그룹 삭제 요청 id: {}",id);
        groupService.delete(id);

    }
    @GetMapping("api/groups")
    public List<GroupListResponseDto> findAllGroups(){
        return groupService.findAllGroups();
    }
}
