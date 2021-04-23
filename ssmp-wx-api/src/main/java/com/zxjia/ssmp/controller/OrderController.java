package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.service.OrderService;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单结算")
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "订单列表")
    @PostMapping("/orders")
    public ResultVO<OrderListVo> orders(@RequestBody OrderRequest orderRequest) {
        return ResultVO.success(orderService.orders(orderRequest));
    }

    @ApiOperation(value = "订单结算")
    @PostMapping("/bill")
    public ResultVO<OrderVo> bill(@RequestBody OrderRequest orderRequest) {
        return ResultVO.success(orderService.bill(orderRequest));
    }

    @ApiOperation(value = "创建订单")
    @PostMapping("/createOrder")
    public ResultVO<OrderVo> createOrder(@RequestBody @Validated OrderRequest orderRequest) {
        return ResultVO.success(orderService.createOrder(orderRequest));
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("/updateOrder")
    public ResultVO<Boolean> updateOrder(@RequestBody OrderRequest orderRequest) {
        return ResultVO.success(orderService.updateOrder(orderRequest));
    }

    @ApiOperation(value = "订单评价")
    @PostMapping(value = "/createOrderComments")
    public ResultVO<Boolean> createOrderComments(@RequestBody OrderCommentRequest orderCommentRequest) {
        return ResultVO.success(orderService.createOrderComments(orderCommentRequest));
    }

}
