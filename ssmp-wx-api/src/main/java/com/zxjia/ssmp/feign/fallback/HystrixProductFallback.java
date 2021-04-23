package com.zxjia.ssmp.feign.fallback;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.entity.Cart;
import com.zxjia.ssmp.feign.ProductApiService;
import com.zxjia.ssmp.vo.CartVo;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HystrixProductFallback implements FallbackFactory<ProductApiService> {
    @Override
    public ProductApiService create(Throwable throwable) {
        return new ProductApiService() {
            @Override
            public List<ProductVo> findAll() {
                return null;
            }

            @Override
            public List<ProductCateVo> getProductCate() {
                return null;
            }

            @Override
            public List<ProductVo> getProductByCatId(Integer catId) {
                return null;
            }

            @Override
            public List<ProductImageVo> getProductImageById(Integer productId) {
                return null;
            }

            @Override
            public ProductVo getProductById(Integer productId) {
                return null;
            }

            @Override
            public boolean addCart(CartRequest request) {
                return false;
            }

            @Override
            public List<CartVo> getCart(CartRequest request) {
                return null;
            }

            @Override
            public boolean updateCart(Cart cart) {
                return false;
            }

            @Override
            public boolean deleteCart(CartRequest cartRequest) {
                return false;
            }

            @Override
            public List<ProductVo> getProductByIds(ProductRequest request) {
                return null;
            }

            @Override
            public boolean updateProduct(ProductRequest request) {
                return false;
            }
        };
    }
}
