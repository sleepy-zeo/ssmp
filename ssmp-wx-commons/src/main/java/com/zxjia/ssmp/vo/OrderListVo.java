package com.zxjia.ssmp.vo;

import com.zxjia.ssmp.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderListVo {

    private List<Orders> ordersList;
}
