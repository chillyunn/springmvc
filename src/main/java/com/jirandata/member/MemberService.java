package com.jirandata.member;

import com.jirandata.member.dtos.MemberDataTableResponseDto;
import com.jirandata.member.dtos.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public void save(MemberRequestDto requestDto) {
        memberRepository.save(encodePassword(requestDto).toEntity());
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
    public Page<Member> pageList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    private MemberRequestDto encodePassword(MemberRequestDto requestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        requestDto.changePlainToCipher(encodedPassword);
        return requestDto;
    }

    @Transactional
    public MemberDataTableResponseDto findAllServerSide(MemberDataTableResponseDto responseDto, @RequestBody MultiValueMap<String, String> map) {
        int draw = Integer.parseInt(map.get("draw").get(0));
        int start = Integer.parseInt(map.get("start").get(0));
        int length = Integer.parseInt(map.get("length").get(0));
        String[] searchParams = new String[]{
                map.get("columns[1][search][value]").get(0),
                map.get("columns[2][search][value]").get(0),
                map.get("columns[4][search][value]").get(0),
                map.get("columns[5][search][value]").get(0),
                map.get("columns[6][search][value]").get(0),
        };
        log.info("draw: {}", draw);
        log.info("start: {}", start);
        log.info("length: {}", length);
        Arrays.stream(searchParams).forEach(p -> log.info(p));

       // int total = (int) memberRepository.count();

        int page = getPage(start, length);

        Pageable pageable = PageRequest.of(page, length);

        int total = memberQueryRepository.findCountByColumnsArrayPageable(searchParams).intValue();
        PageImpl<Member> data = memberQueryRepository.findAllColumnsArrayPageable(searchParams, pageable);
        // List data = memberRepository.findAll(PageRequest.of(page,length)).getContent();

        return responseDto.builder()
                .draw(draw)
                .recordsTotal(total)
                .recordsFilterd(total)
                .data(data.getContent())
                .build();
    }

    private int getPage(int start, int length) {
        return start / length;
    }
}
