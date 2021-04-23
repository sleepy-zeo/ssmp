package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.feign.OrderApiService;
import com.zxjia.ssmp.vo.OrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderApiService orderApiService;

    public OrderListVo orders(OrderRequest orderRequest) {
        return orderApiService.orders(orderRequest);
    }

    public void cancel(OrderRequest orderRequest) {
        orderApiService.updateOrder(orderRequest);
    }
}
