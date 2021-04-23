package com.zxjia.ssmp.feign;

import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.feign.fallback.HystrixUserFallback;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(value = "ssmp-wx-api-user", fallbackFactory = HystrixUserFallback.class)
@RequestMapping("/api")
public interface UserApiService {

    @PostMapping(value = "/user/getmemberAddress")
    List<MemberAddressVo> getMemberAddress(@RequestBody MemberAddressRequest request);

    @PostMapping(value = "/user/getMemberByMobile")
    MemberVo getMemberByMobile(@RequestBody MemberRequest request);

    @PostMapping(value = "/user/addMember")
    MemberVo addMember(@RequestBody MemberRequest memberVo);

    @PostMapping(value = "/user/sendsms")
    boolean sendSms(@RequestBody MemberRequest memberRequest);

    @PostMapping(value = "/user/addmemberaddress")
    boolean addMemberAddress(@RequestBody MemberAddressVo memberAddressVo);

    @PostMapping(value = "/user/updatememberaddress")
    boolean updateMemberAddress(@RequestBody MemberAddressVo memberAddressVo);

    @PostMapping(value = "/user/deletememberaddress")
    boolean deleteMemberAddress(@RequestBody MemberAddressVo memberAddressVo);

    @PostMapping(value = "/user/getmemberaddressbyid")
    MemberAddressVo getMemberAddressInfo(@RequestBody MemberAddressRequest request);
}
