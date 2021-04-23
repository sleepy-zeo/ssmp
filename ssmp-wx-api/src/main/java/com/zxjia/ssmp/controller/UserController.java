package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.MemberAddressRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.service.UserService;
import com.zxjia.ssmp.vo.MemberAddressVo;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/user/login")
    public ResultVO login(@RequestBody MemberRequest memberRequest) {
        return ResultVO.success(userService.login(memberRequest));
    }

    @ApiOperation(value = "发送短信验证码")
    @PostMapping(value = "/user/sendSms")
    public ResultVO sendSms(@RequestBody MemberRequest memberRequest) {
        return ResultVO.success(userService.sendSms(memberRequest));
    }

    @ApiOperation(value = "获取用户地址列表")
    @PostMapping(value = "/user/getMemberAddress")
    public ResultVO<List<MemberAddressVo>> getMemberAddress(@RequestBody MemberAddressRequest request) {
        return ResultVO.success(userService.getMemberAddress(request));
    }

    @ApiOperation(value = "添加用户地址")
    @PostMapping(value = "/user/addMemberAddress")
    public ResultVO<Boolean> addMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return ResultVO.success(userService.addMemberAddress(memberAddressVo));
    }

    @ApiOperation(value = "修改用户地址")
    @PostMapping(value = "/user/updateMemberAddress")
    public ResultVO<Boolean> updateMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return ResultVO.success(userService.updateMemberAddress(memberAddressVo));
    }

    @ApiOperation(value = "删除用户地址")
    @PostMapping(value = "/user/deleteMemberAddress")
    public ResultVO<Boolean> deleteMemberAddress(@RequestBody MemberAddressVo memberAddressVo) {
        return ResultVO.success(userService.deleteMemberAddress(memberAddressVo));
    }

    @ApiOperation(value = "查询地址信息")
    @PostMapping(value = "/user/getMemberAddressInfo")
    public ResultVO<MemberAddressVo> getMemberAddressInfo(@RequestBody MemberAddressRequest request) {
        return ResultVO.success(userService.getMemberAddressInfo(request));
    }
}
