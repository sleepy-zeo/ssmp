package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxjia.ssmp.vo.ProductVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@TableName("orders")
@Data
public class Orders {

    public static final String ORDER_STATUS_1 = "1";
    public static final String ORDER_STATUS_2 = "2";
    public static final String ORDER_STATUS_3 = "3";
    public static final String ORDER_STATUS_4 = "4";
    public static final String ORDER_STATUS_8 = "8";

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderSn;

    private String paySn;

    private String realName;

    private String mobile;

    private String address;

    private BigDecimal orderMoney;

    private String orderStatus;

    private Date createTime;

    private String source;

    private Integer memberId;

    @TableField(exist = false)
    private List<OrdersProduct> ordersProducts;
    @TableField(exist = false)
    private List<ProductVo> productVoList;
    @TableField(exist = false)
    private String createDate;
    @TableField(exist = false)
    private String payStatus;
}
