package com.zxjia.ssmp.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.feign.ProductApiService;
import com.zxjia.ssmp.feign.UserApiService;
import com.zxjia.ssmp.service.CartService;
import com.zxjia.ssmp.utils.ObjectUtil;
import com.zxjia.ssmp.vo.CartListVo;
import com.zxjia.ssmp.vo.CartVo;
import com.zxjia.ssmp.vo.MemberVo;
import com.zxjia.ssmp.vo.ProductVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductApiService productApiService;
    @Autowired
    UserApiService userApiService;

    @Override
    @Transactional
    public boolean addCart(CartRequest request) {
        MemberVo memberVo = userApiService.getMemberByMobile(ObjectUtil.initObject(new MemberRequest())
                .init(v -> v.setMobile(request.getMemberMobile())).getObject());
        if (memberVo == null) {
            throw new BusinessException("用户不存在");
        }
        ProductVo productVo = productApiService.getProductById(request.getProductId());
        if (ObjectUtils.isEmpty(productVo)) {
            throw new BusinessException("商品不存在");
        }
        if (productVo.getStock() < 1) {
            throw new BusinessException("商品库存不足");
        }
        request.setMemberId(memberVo.getId());
        List<CartVo> cartVos = productApiService.getCart(request);
        if (CollectionUtil.isEmpty(cartVos)) {
            productApiService.addCart(request);
        } else {
            CartVo cartVo = cartVos.stream().findFirst().orElse(null);
            cartVo.setNumber(cartVo.getNumber() + request.getNumber());
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartVo, cart);

            productApiService.updateCart(cart);
        }
        return true;
    }

    @Override
    public CartListVo getCart(CartRequest cartRequest) {
        CartListVo cartListVo = new CartListVo();
        List<CartVo> cartList = productApiService.getCart(cartRequest);
        for (CartVo cart : cartList) {
            ProductVo productVo = productApiService.getProductById(cart.getProductId());
            cart.setProductVo(productVo);
        }
        cartListVo.setCart(cartList);
        return cartListVo;
    }

    @Override
    public boolean updateCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartRequest, cart);

        return productApiService.updateCart(cart);
    }

    @Override
    public boolean deleteCart(CartRequest cartRequest) {
        return productApiService.deleteCart(cartRequest);
    }
}
