package com.zxjia.ssmp.vo;

import com.zxjia.ssmp.entity.Orders;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderVo {

    private BigDecimal totalMoney;

    private List<CartVo> cartListVos;

    private MemberAddressVo memberAddressVo;

    private List<Orders> ordersList;
}
