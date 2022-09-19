package com.jirandata.member;

import com.jirandata.member.dtos.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberRequestDto requestDto) {
        memberRepository.save(requestDto.toEntity());
    }

    @Transactional
    public Member update(Long id,MemberRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        log.info(member.toString());
        member.changeMember(requestDto);
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
