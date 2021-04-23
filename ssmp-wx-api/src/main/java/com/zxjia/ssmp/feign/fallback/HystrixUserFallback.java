package com.zxjia.ssmp.feign.fallback;

import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.feign.UserApiService;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HystrixUserFallback implements FallbackFactory<UserApiService> {

    @Override
    public UserApiService create(Throwable throwable) {
        return new UserApiService() {
            @Override
            public List<MemberAddressVo> getMemberAddress(MemberAddressRequest request) {
                return null;
            }

            @Override
            public MemberVo getMemberByMobile(MemberRequest request) {
                return null;
            }

            @Override
            public MemberVo addMember(MemberRequest memberVo) {
                return null;
            }

            @Override
            public boolean sendSms(MemberRequest memberRequest) {
                return false;
            }

            @Override
            public boolean addMemberAddress(MemberAddressVo memberAddressVo) {
                return false;
            }

            @Override
            public boolean updateMemberAddress(MemberAddressVo memberAddressVo) {
                return false;
            }

            @Override
            public boolean deleteMemberAddress(MemberAddressVo memberAddressVo) {
                return false;
            }

            @Override
            public MemberAddressVo getMemberAddressInfo(MemberAddressRequest request) {
                return null;
            }
        };
    }
}
