package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.OrdersVo;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    @Transactional
    OrderVo createOrder(OrderRequest order);

    OrderListVo orders(OrderRequest orderRequest);

    boolean updateOrder(OrderRequest orderRequest);

    OrdersVo getOrder(OrderRequest orderRequest);

    boolean createOrderComments(OrderCommentRequest request);
}
