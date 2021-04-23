package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.service.UserService;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取会员地址列表
     * @param request
     * @return
     */
    @PostMapping(value = "/user/getmemberAddress")
    public List<MemberAddressVo> getMemberAddress(@RequestBody MemberAddressRequest request) {
        return userService.getMemberAddress(request);
    }

    /**
     * 根据手机号查询用户信息
     * @param memberRequest
     * @return
     */
    @PostMapping(value = "/user/getMemberByMobile")
    public MemberVo getMemberByMobile(@RequestBody MemberRequest memberRequest) {
        return userService.getMemberByMobile(memberRequest);
    }

    /**
     * 发送短信
     * @param memberRequest
     * @return
     */
    @PostMapping(value = "/user/sendsms")
    public boolean sendSms(@RequestBody MemberRequest memberRequest) {
        return userService.sendSms(memberRequest);
    }

    /**
     * 用户信息创建
     * @param memberRequest
     * @return
     */
    @PostMapping(value = "/user/addMember")
    public MemberVo addMember(@RequestBody MemberRequest memberRequest) {
        return userService.addMember(memberRequest);
    }

    /**
     * 添加用户地址信息
     * @param memberAddressVo
     * @return
     */
    @PostMapping(value = "/user/addmemberaddress")
    public boolean addMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return userService.addMemberAddress(memberAddressVo);
    }

    /**
     * 修改用户地址
     * @param memberAddressVo
     * @return
     */
    @PostMapping(value = "/user/updatememberaddress")
    public boolean updateMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return userService.updateMemberAddress(memberAddressVo);
    }

    /**
     * 删除用户地址
     * @param memberAddressVo
     * @return
     */
    @PostMapping(value = "/user/deletememberaddress")
    public boolean deleteMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return userService.deleteMemberAddress(memberAddressVo);
    }

    /**
     * 根据地址id查询
     * @return
     */
    @PostMapping(value = "/user/getmemberaddressbyid")
    public MemberAddressVo getMemberAddressById(@RequestBody MemberAddressRequest request) {
        return userService.getMemberAddressById(request);
    }
}
