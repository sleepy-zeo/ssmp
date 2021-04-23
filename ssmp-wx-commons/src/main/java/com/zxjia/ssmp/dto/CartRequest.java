package com.zxjia.ssmp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {

    private Integer id;

    private Integer memberId;

    private Integer productId;

    private List<Integer> productIds;

    private Integer number;

    private String memberMobile;
}
