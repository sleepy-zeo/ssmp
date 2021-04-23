package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;

public interface OrderService {

    OrderVo bill(OrderRequest orderRequest);

    OrderVo createOrder(OrderRequest orderRequest);

    OrderListVo orders(OrderRequest orderRequest);

    boolean updateOrder(OrderRequest orderRequest);

    boolean createOrderComments(OrderCommentRequest request);
}
