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
    public MemberDataTableResponseDto findAllServerSide(MemberDataTableResponseDto responseDto,  MultiValueMap<String, String> map) {
        // 통신횟수, 가져오기시작할 열 번호, 가져올 열의 길이 획득
        int draw = Integer.parseInt(map.get("draw").get(0));
        int start = Integer.parseInt(map.get("start").get(0));
        int length = Integer.parseInt(map.get("length").get(0));

        // 검색조건 및 키워드 획득
        MemberSearchType memberSearchType = MemberSearchType.from(map.get("searchType").get(0));
        String keyword = map.get("searchValue").get(0);

        //조회해야하는 페이지 구하기
        int page = getPage(start, length);

        Pageable pageable = PageRequest.of(page, length);
        //조회했을 때의 전체 페이지 수
        int total = memberQueryRepository.findCountByColumnsArrayPageable(memberSearchType, keyword).intValue();
        //조회했을 때의 데이터
        PageImpl<Member> data = memberQueryRepository.findAllColumnsArrayPageable(memberSearchType, keyword, pageable);


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
