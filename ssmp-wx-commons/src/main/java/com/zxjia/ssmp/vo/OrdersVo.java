package com.zxjia.ssmp.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrdersVo {

    private Integer id;

    private BigDecimal orderMoney;

    private BigDecimal discount;

    private Integer memberId;

    private Integer productId;

    private String mobile;

    private String addressId;

    private String address;

    private List<CartVo> cart;

    private String orderStatus;

    private Date createTime;

    private String source;

    private String realName;

    private String orderSn;

}
