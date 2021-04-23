package com.zxjia.ssmp.feign;

import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.vo.OrderListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "ssmp-wx-api-order")
@RequestMapping("/api/order")
public interface OrderApiService {

    @PostMapping(value = "/updateorder")
    boolean updateOrder(@RequestBody OrderRequest orderRequest);

    @PostMapping(value = "/orderss")
    OrderListVo orders(@RequestBody OrderRequest orderRequest);
}
