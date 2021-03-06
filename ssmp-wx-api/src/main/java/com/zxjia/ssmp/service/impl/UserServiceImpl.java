package com.zxjia.ssmp.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.feign.UserApiService;
import com.zxjia.ssmp.service.UserService;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserApiService userApiService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ApplicationContext context;

    @Override
    public List<MemberAddressVo> getMemberAddress(MemberAddressRequest request) {
        return userApiService.getMemberAddress(request);
    }

    @Override
    public MemberVo getMemberByMobile(MemberRequest memberRequest) {
        return userApiService.getMemberByMobile(memberRequest);
    }

    @Override
    public boolean addMemberAddress(MemberAddressVo memberAddressVo) {
        return userApiService.addMemberAddress(memberAddressVo);
    }

    @Override
    public boolean updateMemberAddress(MemberAddressVo memberAddressVo) {
        return userApiService.updateMemberAddress(memberAddressVo);
    }

    @Override
    public boolean deleteMemberAddress(MemberAddressVo memberAddressVo) {
        return userApiService.deleteMemberAddress(memberAddressVo);
    }

    @Override
    public MemberAddressVo getMemberAddressInfo(MemberAddressRequest request) {
        return userApiService.getMemberAddressInfo(request);
    }

    @Override
    public MemberVo login(MemberRequest request) {
        check(request);

        MemberVo memberVo = userApiService.getMemberByMobile(request);
        if (memberVo == null) {
            memberVo = userApiService.addMember(request);
        }
        return memberVo;
    }

    @Override
    public boolean sendSms(MemberRequest memberRequest) {
        String profiles = context.getEnvironment().getActiveProfiles()[0];
        if (StrUtil.compare(profiles, "dev", true) == 0) {
            throw new BusinessException("??????????????????8888");
        }
        Object obj = stringRedisTemplate.opsForValue().get("sms");
        if (obj != null) {
            throw new BusinessException("????????????????????????");
        } else {
            try {
                String message = RandomUtil.randomNumbers(4);
                memberRequest.setMessage(message);
                userApiService.sendSms(memberRequest);
                stringRedisTemplate.opsForValue().set("sms", message, 60L, TimeUnit.SECONDS);
            } catch (BusinessException e) {
                throw new BusinessException("???????????????????????????????????????");
            }
        }
        return true;
    }

    private void check(MemberRequest request) {
        String profiles = context.getEnvironment().getActiveProfiles()[0];
        if (StrUtil.compare(profiles, "dev", true) == 0) {
            if (!request.getMessage().equals("8888")) {
                throw new BusinessException("?????????????????????");
            }
        }
        if (StrUtil.compare(profiles, "prod", true) == 0) {
            String code = stringRedisTemplate.opsForValue().get("sms");
            if (StringUtils.isEmpty(code)) {
                throw new BusinessException("????????????????????????????????????");
            }
            if (StrUtil.compare(request.getMessage(), code, true) != 0) {
                throw new BusinessException("????????????????????????");
            }
        }
    }
}
