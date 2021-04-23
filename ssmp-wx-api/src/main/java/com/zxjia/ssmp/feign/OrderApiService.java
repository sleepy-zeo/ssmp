package com.zxjia.ssmp.feign;

import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.feign.fallback.HystrixOrderFallback;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.OrdersVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "ssmp-wx-api-order", fallbackFactory = HystrixOrderFallback.class)
@RequestMapping("/api/order")
public interface OrderApiService {

    @PostMapping(value = "/createorder")
    OrderVo createOrder(@RequestBody OrderRequest orderRequest);

    @PostMapping(value = "/orderss")
    OrderListVo orders(@RequestBody OrderRequest orderRequest);

    @PostMapping(value = "/getorder")
    OrdersVo getOrder(@RequestBody OrderRequest orderRequest);

    @PostMapping(value = "/updateorder")
    boolean updateOrder(@RequestBody OrderRequest orderRequest);

    @PostMapping(value = "/createordercomments")
    boolean createOrderComments(@RequestBody OrderCommentRequest orderCommentRequest);
}
