package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.service.CartService;
import com.zxjia.ssmp.vo.CartListVo;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车管理
 */
@Api(tags = "购物车管理")
@RestController
@RequestMapping(value = "/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "添加购物车")
    @PostMapping(value = "/cart/addCart")
    public ResultVO<Boolean> addCart(@RequestBody CartRequest request) {
        return ResultVO.success(cartService.addCart(request));
    }

    @ApiOperation(value = "查询购物车列表")
    @PostMapping(value = "/cart/getCart")
    public ResultVO<CartListVo> getCart(@RequestBody CartRequest cartRequest) {
        return ResultVO.success(cartService.getCart(cartRequest));
    }

    @ApiOperation(value = "修改购物车列表")
    @PostMapping(value = "/cart/updateCart")
    public ResultVO<CartListVo> updateCart(@RequestBody CartRequest cartRequest) {
        return ResultVO.success(cartService.updateCart(cartRequest));
    }

    @ApiOperation(value = "删除购物车列表")
    @PostMapping(value = "/cart/deleteCart")
    public ResultVO<CartListVo> deleteCart(@RequestBody CartRequest cartRequest) {
        return ResultVO.success(cartService.deleteCart(cartRequest));
    }

}
