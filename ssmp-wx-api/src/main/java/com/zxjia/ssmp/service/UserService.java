package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;

import java.util.List;

public interface UserService {

    List<MemberAddressVo> getMemberAddress(MemberAddressRequest request);

    MemberVo getMemberByMobile(MemberRequest memberRequest);

    MemberVo login(MemberRequest memberRequest);

    boolean sendSms(MemberRequest memberRequest);

    boolean addMemberAddress(MemberAddressVo memberAddressVo);

    boolean updateMemberAddress(MemberAddressVo memberAddressVo);

    boolean deleteMemberAddress(MemberAddressVo memberAddressVo);

    MemberAddressVo getMemberAddressInfo(MemberAddressRequest request);
}
