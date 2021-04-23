package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.service.OrderService;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.OrdersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/createorder")
    public OrderVo createOrder(@RequestBody OrderRequest order) {
        return orderService.createOrder(order);
    }

    @PostMapping(value = "/orderss")
    public OrderListVo orders(@RequestBody OrderRequest orderRequest) {
        return orderService.orders(orderRequest);
    }

    @PostMapping(value = "/getorder")
    public OrdersVo getOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.getOrder(orderRequest);
    }

    @PostMapping(value = "/updateorder")
    public boolean updateOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(orderRequest);
    }

    @PostMapping(value = "/createordercomments")
    public boolean createOrderComments(@RequestBody OrderCommentRequest orderCommentRequest) {
        return orderService.createOrderComments(orderCommentRequest);
    }
}
