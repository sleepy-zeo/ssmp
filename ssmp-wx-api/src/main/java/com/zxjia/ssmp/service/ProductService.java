package com.zxjia.ssmp.service;

import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.dto.IndexRequest;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.vo.IndexVo;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;

import java.util.List;

public interface ProductService {

    List<ProductVo> findAll();

    ProductVo getProductById(Integer productId);

    List<ProductVo> getProductByIds(ProductRequest request);

    List<ProductCateVo> getProductCate();

    List<ProductVo> getProductByCateId(Integer catId);

    List<ProductImageVo> getProductImageById(Integer productId);

    boolean addCart(CartRequest request);

    IndexVo getProductCateById(Integer catId);

    IndexVo search(IndexRequest request);

    boolean updateProduct(ProductRequest request);
}

