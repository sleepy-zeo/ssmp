package com.zxjia.ssmp.feign;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.feign.fallback.HystrixProductFallback;
import com.zxjia.ssmp.vo.CartVo;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "ssmp-wx-api-product", fallbackFactory = HystrixProductFallback.class)
@RequestMapping("/api")
public interface ProductApiService {

    @GetMapping(value = "/product/findAll")
    List<ProductVo> findAll();

    @GetMapping(value = "/product/getProductCate")
    List<ProductCateVo> getProductCate();

    @PostMapping(value = "/product/getProductByCatId")
    List<ProductVo> getProductByCatId(@RequestParam("catId") Integer catId);

    @PostMapping(value = "/product/getProductImageById")
    List<ProductImageVo> getProductImageById(@RequestParam("productId") Integer productId);

    @PostMapping(value = "/product/getProductById")
    ProductVo getProductById(@RequestParam("productId") Integer productId);

    @PostMapping(value = "/cart/addcart")
    boolean addCart(@RequestBody CartRequest request);

    @PostMapping(value = "/cart/getcart")
    List<CartVo> getCart(@RequestBody CartRequest request);

    @PostMapping(value = "/cart/updatecart")
    boolean updateCart(Cart cart);

    @PostMapping(value = "/cart/deletecart")
    boolean deleteCart(@RequestBody CartRequest cartRequest);

    @PostMapping(value = "/product/getProductByIds")
    List<ProductVo> getProductByIds(@RequestBody ProductRequest request);

    @PostMapping(value = "/product/updateProduct")
    boolean updateProduct(@RequestBody ProductRequest request);
}
