package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("cart")
@Data
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private Integer productId;

    private Integer number;
}
