package com.zxjia.ssmp.job;

import cn.hutool.core.collection.CollectionUtil;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.entity.Orders;
import com.zxjia.ssmp.service.OrderService;
import com.zxjia.ssmp.utils.ObjectUtil;
import com.zxjia.ssmp.vo.OrderListVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    OrderService orderService;

    // 每隔30分钟，将所有未付款的订单取消
    @Scheduled(cron = "0 0/30 * * * ?")
    public void cancelOrder() {
        OrderRequest orderRequest = ObjectUtil.initObject(new OrderRequest())
                .init(v -> v.setOrderStatus(OrderRequest.STATUS_1)).getObject();
        OrderListVo orderListVo = orderService.orders(orderRequest);

        if (ObjectUtils.isNotEmpty(orderListVo)) {
            List<Orders> ordersList = orderListVo.getOrdersList();
            if (CollectionUtil.isNotEmpty(ordersList)) {
                ordersList.forEach(order -> {
                    OrderRequest request = new OrderRequest();
                    BeanUtils.copyProperties(order, request);
                    request.setOrderStatus(OrderRequest.STATUS_8);
                    orderService.cancel(request);
                });
            }
        }
    }
}