package com.zxjia.ssmp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {

    List<Integer> productIds;

    private Integer id;
}
