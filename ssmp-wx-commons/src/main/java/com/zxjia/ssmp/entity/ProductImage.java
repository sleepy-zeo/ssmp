package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("product_image")
@Data
public class ProductImage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer productId;

    private String image;
}
