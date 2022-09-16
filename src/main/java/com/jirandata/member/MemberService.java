package com.jirandata.member;

import com.jirandata.member.dtos.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto){
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
