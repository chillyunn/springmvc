package com.jirandata.member;

import com.jirandata.common.dto.DataTableResponseDto;
import com.jirandata.member.dtos.MemberRegionChartResponseDto;
import com.jirandata.member.dtos.MemberRequestDto;
import com.jirandata.member.repository.MemberQueryRepository;
import com.jirandata.member.repository.MemberRepository;
import com.jirandata.member.repository.MemberSearchType;
import com.jirandata.member.repository.RegionCount;
import com.jirandata.util.DatatablePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

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
    public DataTableResponseDto findAllServerSide(DataTableResponseDto responseDto, MultiValueMap<String, String> map) {
        DatatablePayload datatablePayload = new DatatablePayload(map);
        Pageable pageable = datatablePayload.getPageable();
        // 검색조건 및 키워드 획득
        MemberSearchType memberSearchType = MemberSearchType.from(datatablePayload.getSearchType());
        String keyword = datatablePayload.getSearchValue();
        //조회했을 때의 전체 페이지 수
        long total = memberQueryRepository.findCountByColumnsArrayPageable(memberSearchType, keyword).intValue();
        //조회했을 때의 데이터
        PageImpl<Member> data = memberQueryRepository.findAllColumnsArrayPageable(memberSearchType, keyword, datatablePayload.getColumnIndex(), datatablePayload.getOrderDirection(), pageable);


        return responseDto.builder()
                .draw(datatablePayload.getDraw())
                .recordsTotal(total)
                .recordsFilterd(total)
                .data(data.getContent())
                .build();
    }
    public MemberRegionChartResponseDto findAllGroupByRegion() {
        List<RegionCount> regionCountList = memberRepository.countMemberByRegionInterface();
        List<String> regionList = regionCountList.stream().map(r->r.getRegion()).collect(Collectors.toList());
        List<Long> countList = regionCountList.stream().map(c->c.getCount()).collect(Collectors.toList());
        MemberRegionChartResponseDto responseDto = new MemberRegionChartResponseDto(regionList,countList);
        return responseDto;
    }

}
