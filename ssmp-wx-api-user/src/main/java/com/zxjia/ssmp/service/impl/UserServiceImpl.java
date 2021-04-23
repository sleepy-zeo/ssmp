package com.zxjia.ssmp.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.entity.Member;
import com.zxjia.ssmp.entity.MemberAddress;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.mapper.MemberMapper;
import com.zxjia.ssmp.mapper.UserMapper;
import com.zxjia.ssmp.service.UserService;
import com.zxjia.ssmp.utils.ObjectUtil;
import com.zxjia.ssmp.utils.SendSms;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, MemberAddress> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SendSms sendSms;
    @Autowired
    MemberMapper memberMapper;

    @Override
    public List<MemberAddressVo> getMemberAddress(MemberAddressRequest request) {
        List<MemberAddressVo> memberAddressVoList = new ArrayList<>();
        this.list(new QueryWrapper<MemberAddress>().lambda()
                .eq(MemberAddress::getMemberId, request.getMemberId()))
                .forEach((e) -> {
                    MemberAddressVo memberAddressVo = new MemberAddressVo();
                    memberAddressVo.setAddress(e.getAddress());
                    memberAddressVo.setId(e.getId());
                    memberAddressVo.setIsDefault(e.getIsDefault());
                    memberAddressVo.setMobile(e.getMobile());
                    memberAddressVo.setRealName(e.getRealName());
                    memberAddressVoList.add(memberAddressVo);
                });
        return memberAddressVoList;
    }

    @Override
    public MemberAddressVo getMemberAddressById(MemberAddressRequest request) {
        MemberAddress memberAddress = this.getOne(new QueryWrapper<MemberAddress>().lambda()
                .eq(MemberAddress::getId, request.getId())
        );
        MemberAddressVo memberAddressVo = new MemberAddressVo();
        BeanUtils.copyProperties(memberAddress, memberAddressVo);
        return memberAddressVo;
    }

    @Override
    public boolean addMemberAddress(MemberAddressVo memberAddressVo) {
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressVo, memberAddress);
        userMapper.insert(memberAddress);
        return true;
    }

    @Override
    public boolean updateMemberAddress(MemberAddressVo memberAddressVo) {
        MemberAddress memberAddress = userMapper.selectById(memberAddressVo.getId());
        if (memberAddress == null) {
            throw new BusinessException("用户地址不存在");
        }
        BeanUtils.copyProperties(memberAddressVo, memberAddress);

        if (null != memberAddressVo.getMemberId() && !"null".equals("" + memberAddress.getMemberId())) {
            //在做更新的时候先把所有地址状态都设置为无效
            userMapper.update(ObjectUtil.initObject(new MemberAddress())
                    .init(v -> v.setIsDefault("1")).getObject(), new UpdateWrapper<MemberAddress>().lambda()
                    .eq(MemberAddress::getMemberId, memberAddress.getMemberId()));
        }
        //然后根据id再去修改
        userMapper.update(memberAddress, new UpdateWrapper<MemberAddress>().lambda()
                .eq(MemberAddress::getId, memberAddress.getId()));

        return true;
    }

    @Override
    public boolean deleteMemberAddress(MemberAddressVo memberAddressVo) {

        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressVo, memberAddress);
        userMapper.delete(new UpdateWrapper<MemberAddress>().lambda().eq(MemberAddress::getId, memberAddress.getId()));
        return true;
    }

    @Override
    public MemberVo getMemberByMobile(MemberRequest memberRequest) {
        Member member = userMapper.getMemberByMobile(memberRequest.getMobile());
        if (member == null) {
            return null;
        }
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member, memberVo);
        return memberVo;
    }

    @Override
    public boolean sendSms(MemberRequest memberRequest) {
        return sendSms.sendSms(memberRequest);
    }

    @Override
    public MemberVo addMember(MemberRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        member.setNickName("用户" + RandomUtil.randomNumbers(8));
        member.setGender("1");
        member.setSource("1");
        memberMapper.insert(member);

        MemberVo memberVo = getMemberByMobile(request);
        return memberVo;
    }
}
