package com.zxjia.ssmp.feign.fallback;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.feign.OrderApiService;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.OrdersVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixOrderFallback implements FallbackFactory<OrderApiService> {

    @Override
    public OrderApiService create(Throwable throwable) {
        return new OrderApiService() {
            @Override
            public OrderVo createOrder(OrderRequest orderRequest) {
                return null;
            }

            @Override
            public OrderListVo orders(OrderRequest orderRequest) {
                return null;
            }

            @Override
            public OrdersVo getOrder(OrderRequest orderRequest) {
                return null;
            }

            @Override
            public boolean updateOrder(OrderRequest orderRequest) {
                return false;
            }

            @Override
            public boolean createOrderComments(OrderCommentRequest orderCommentRequest) {
                return false;
            }
        };
    }

}
