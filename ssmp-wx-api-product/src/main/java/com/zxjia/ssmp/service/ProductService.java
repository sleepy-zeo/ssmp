package com.zxjia.ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.entity.Product;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<ProductVo> getProductByCateId(Integer catId);

    List<ProductImageVo> getProductImageById(Integer productId);

    ProductVo getProductById(Integer productId);

    List<ProductVo> getProductByIds(ProductRequest request);

    List<ProductVo> findAll();
}
