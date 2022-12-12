package com.jirandata.policy;

import com.jirandata.common.dto.DataTableResponseDto;
import com.jirandata.policy.dto.PolicySaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PolicyApiController {
    private final PolicyService policyService;

    @PostMapping("/api/policy")
    public void save(@RequestBody PolicySaveRequestDto requestDto){
        log.info("정책 생성 요청: {}",requestDto.toString());
        policyService.save(requestDto);
    }
    @PostMapping("/api/policies")
    public DataTableResponseDto findAll(@RequestBody MultiValueMap<String,String> map){
        log.info("정책 조회 요청");
        return policyService.findAll(map);
    }
    @PostMapping("/api/policies/delete")
    public void deletePolicy(@RequestBody List<Long> ids){
        log.info("정책 삭제 요청");
        policyService.disapplyPolicyBeforeDelete(ids);
        policyService.delete(ids);
    }
}
