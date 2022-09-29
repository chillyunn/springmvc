package com.jirandata.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jirandata.member.dtos.MemberDataTableResponseDto;
import com.jirandata.member.dtos.MemberRegionResponseDto;
import com.jirandata.member.dtos.MemberRequestDto;
import com.jirandata.member.repository.RegionCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;
    ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/api/members")
    public Map<String,Object> findAll(){
        List<Member> data = memberService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        return map;
    }
    @PostMapping( "/api/member")
    public void save(@RequestBody MemberRequestDto requestDto){
        log.info(requestDto.toString());
        memberService.save(requestDto);
    }
    @PutMapping("/api/member/{id}")
    public void update(@PathVariable Long id,@RequestBody MemberRequestDto requestDto){
        log.info(memberService.update(id,requestDto).toString());
    }
    @DeleteMapping("/api/member/{id}")
    public void delete(@PathVariable Long id){
        memberService.delete(id);
    }

    @GetMapping("/api/members")
    public Page<Member> pageList(@RequestParam Integer page,@RequestParam Integer size)
    {
        PageRequest pageable = PageRequest.of(page,size);
        return memberService.pageList(pageable);
    }
    @PostMapping("/api/members/list")
    public MemberDataTableResponseDto findAllServerSide(MemberDataTableResponseDto responseDto,@RequestBody MultiValueMap<String,String> map){
        return memberService.findAllServerSide(responseDto,map);
    }
    @PostMapping("/api/members/region")
    public List<RegionCount> findAllGroupByRegion(){
        return memberService.findAllGroupByRegion();
    }
}
