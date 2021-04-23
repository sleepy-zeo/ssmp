package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.vo.CartListVo;

public interface CartService {

    boolean addCart(CartRequest request);

    CartListVo getCart(CartRequest cartRequest);

    boolean updateCart(CartRequest cartRequest);

    boolean deleteCart(CartRequest cartRequest);
}
