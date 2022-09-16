package com.jirandata.member;

import com.jirandata.member.dtos.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/members")
    public Map<String,Object> findAll(){
        List<Member> data = memberService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        return map;
    }
    @PostMapping("/api/member")
    public void save(@RequestBody MemberSaveRequestDto requestDto){



//        return memberService.save(requestDto);
    }
}
