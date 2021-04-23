package com.zxjia.ssmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.mapper.CartMapper;
import com.zxjia.ssmp.service.CartService;
import com.zxjia.ssmp.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public boolean addCart(CartRequest request) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(request, cart);
        return this.save(cart);
    }

    @Override
    public List<CartVo> getCart(CartRequest request) {
        List<CartVo> cartVos = new ArrayList<>();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (null != request.getMemberId()) {
            queryWrapper.eq("member_id", request.getMemberId());
        }
        if (null != request.getProductId()) {
            queryWrapper.eq("product_id", request.getProductId());
        }
        List<Cart> listCart = this.list(queryWrapper);

        listCart.forEach(cart -> {
            CartVo cartVo = new CartVo();
            BeanUtils.copyProperties(cart, cartVo);
            cartVos.add(cartVo);
        });
        return cartVos;
    }

    @Override
    public boolean updateCart(Cart cart) {
        return this.updateById(cart);
    }

    @Override
    public boolean deleteCart(CartRequest cartRequest) {
        QueryWrapper queryWrapper = new QueryWrapper<Cart>();
        if (cartRequest.getMemberId() != null) {
            queryWrapper.eq("member_id", cartRequest.getMemberId());
        }
        if (cartRequest.getId() != null) {
            queryWrapper.eq("id", cartRequest.getId());
        }
        return remove(queryWrapper);
    }
}
