package com.jirandata.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jirandata.member.dtos.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/api/member")
    public void save(@RequestBody MemberSaveRequestDto requestDto){
        log.info(requestDto.toString());


//        return memberService.save(requestDto);
    }
}
