package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("order_comments")
@Data
public class OrderComment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private String memberName;

    private String productName;

    private String orderSn;

    private String content;

    private Date createTime;

    private Integer star;

    private String imageSrc;
}
