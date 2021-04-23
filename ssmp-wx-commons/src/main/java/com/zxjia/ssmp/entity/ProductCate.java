package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("product_cate")
@Data
public class ProductCate {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String catName;
}
