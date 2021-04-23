package com.zxjia.ssmp.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductVo {

    private Integer id;

    private Integer productCateId;

    private String productName;

    private String sku;

    private String imgUrl;

    private Integer stock;

    private Integer sales;

    private BigDecimal mallMobilePrice;

    private BigDecimal originalPrice;

    private String description;

    private List<ProductImageVo> productImageVos;
}
