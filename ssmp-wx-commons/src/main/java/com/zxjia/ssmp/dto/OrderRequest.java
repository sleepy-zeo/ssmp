package com.zxjia.ssmp.dto;

import com.zxjia.ssmp.vo.CartVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {

    //订单状态 1 待付款
    public static final String STATUS_1 = "1";
    //已取消
    public static final String STATUS_8 = "8";
    //来源    1  微信
    public static final String SOURCE_1 = "1";

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

    private Integer id;
}
