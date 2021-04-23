package com.zxjia.ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.entity.MemberAddress;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends IService<MemberAddress> {

    List<MemberAddressVo> getMemberAddress(MemberAddressRequest request);

    MemberAddressVo getMemberAddressById(MemberAddressRequest request);

    boolean addMemberAddress(MemberAddressVo memberAddressVo);

    @Transactional
    boolean updateMemberAddress(MemberAddressVo memberAddressVo);

    boolean deleteMemberAddress(MemberAddressVo memberAddressVo);

    MemberVo getMemberByMobile(MemberRequest memberRequest);

    boolean sendSms(MemberRequest memberRequest);

    MemberVo addMember(MemberRequest memberRequest);
}
