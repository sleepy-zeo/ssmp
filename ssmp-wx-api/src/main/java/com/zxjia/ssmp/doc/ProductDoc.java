package com.zxjia.ssmp.doc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "ssmp-product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDoc {

    @Id
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
}