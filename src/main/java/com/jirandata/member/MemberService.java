package com.jirandata.member;

import com.jirandata.member.dtos.MemberDataTableResponseDto;
import com.jirandata.member.dtos.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

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
    public Member update(Long id, MemberRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        log.info(member.toString());
        member.changeMember(requestDto);
        memberRepository.save(member);
        return member;
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Member> pageList(Pageable pageable){
        return memberRepository.findAll(pageable);
    }

    @Transactional
    public MemberDataTableResponseDto findAllServerSide(MemberDataTableResponseDto responseDto,@RequestBody MultiValueMap<String,String> map){
        int draw = Integer.parseInt(map.get("draw").get(0));
        int start = Integer.parseInt(map.get("start").get(0));
        int length = Integer.parseInt(map.get("length").get(0));

        log.info("draw: {}",draw);
        log.info("start: {}",start);
        log.info("length: {}",length);

        int total = (int) memberRepository.count();
        int page = getPage(start,length);
        List data = memberRepository.findAll(PageRequest.of(page,length)).getContent();


        return responseDto.builder()
                .draw(draw)
                .recordsTotal(total)
                .recordsFilterd(total)
                .data(data)
                .build();
    }

    private int getPage(int start, int length) {
        return start / length;
    }
}
