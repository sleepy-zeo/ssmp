package com.zxjia.ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.vo.CartVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService extends IService<Cart> {

    @Transactional
    boolean addCart(CartRequest request);

    List<CartVo> getCart(CartRequest request);

    boolean updateCart(Cart cart);

    boolean deleteCart(CartRequest cartRequest);
}
