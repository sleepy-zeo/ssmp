package com.zxjia.ssmp.vo;

import lombok.Data;

@Data
public class CartVo {

    private Integer id;

    private Integer memberId;

    private Integer productId;

    private Integer number;

    private ProductVo productVo;

}
