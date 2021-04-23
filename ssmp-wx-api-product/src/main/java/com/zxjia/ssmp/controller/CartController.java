package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.service.CartService;
import com.zxjia.ssmp.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(value = "/cart/addcart")
    public boolean addCart(@RequestBody CartRequest request) {
        return cartService.addCart(request);
    }

    @PostMapping(value = "/cart/getcart")
    public List<CartVo> getCart(@RequestBody CartRequest request) {
        return cartService.getCart(request);
    }

    @PostMapping(value = "/cart/updatecart")
    public boolean updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @PostMapping(value = "/cart/deletecart")
    public boolean deleteCart(@RequestBody CartRequest cartRequest) {
        return cartService.deleteCart(cartRequest);
    }
}
