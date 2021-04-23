package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.service.ProductCateService;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    ProductCateService productCateService;

    @Autowired
    ProductService productService;

    @GetMapping(value = "/product/getProductCate")
    public List<ProductCateVo> getProductCate() {
        return productCateService.getProductCate();
    }

    @GetMapping(value = "/product/findAll")
    public List<ProductVo> findAll() {
        return productService.findAll();
    }

    @PostMapping(value = "/product/getProductByCatId")
    public List<ProductVo> getProductByCatId(Integer catId) {
        return productService.getProductByCateId(catId);
    }

    @PostMapping(value = "/product/getProductImageById")
    public List<ProductImageVo> getProductImageById(Integer productId) {
        return productService.getProductImageById(productId);
    }

    @PostMapping(value = "/product/getProductById")
    public ProductVo getProductById(Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/product/getProductByIds")
    public List<ProductVo> getProductByIds(@RequestBody ProductRequest request) {
        return productService.getProductByIds(request);
    }

}
