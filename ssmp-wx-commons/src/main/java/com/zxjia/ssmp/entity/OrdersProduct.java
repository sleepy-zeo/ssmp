package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("orders_product")
@Data
public class OrdersProduct {

    private String orderSn;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productNumber;

    private BigDecimal productMoney;

    @TableField(exist = false)
    private String imgUrl;

}
