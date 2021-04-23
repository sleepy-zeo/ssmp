package com.zxjia.ssmp.service.impl;

import com.zxjia.ssmp.doc.ProductDoc;
import com.zxjia.ssmp.dto.CartRequest;
import com.zxjia.ssmp.dto.IndexRequest;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.feign.ProductApiService;
import com.zxjia.ssmp.service.ProductElasticsearchService;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.IndexVo;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductApiService productApiService;
    @Autowired
    ProductElasticsearchService productElasticsearchService;

    @Override
    public List<ProductCateVo> getProductCate() {
        return productApiService.getProductCate();
    }

    @Override
    public IndexVo getProductCateById(Integer catId) {
        IndexVo indexVo = new IndexVo();
        indexVo.setProductVoList(productApiService.getProductByCatId(catId));
        return indexVo;
    }

    @Override
    public IndexVo search(IndexRequest request) {
        IndexVo indexVo = new IndexVo();

        List<ProductVo> productVoList = new ArrayList<>();

        List<ProductDoc> productDocList = productElasticsearchService.findByProductName(request.getSearch());
        productDocList.stream().forEach(e -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(e, productVo);
            productVoList.add(productVo);
        });
        indexVo.setProductVoList(productVoList);
        return indexVo;
    }

    @Override
    public boolean updateProduct(ProductRequest request) {
        return productApiService.updateProduct(request);
    }

    @Override
    public List<ProductVo> getProductByCateId(Integer catId) {
        return productApiService.getProductByCatId(catId);
    }

    @Override
    public List<ProductImageVo> getProductImageById(Integer productId) {
        return productApiService.getProductImageById(productId);
    }

    @Override
    public List<ProductVo> findAll() {
        return productApiService.findAll();
    }

    @Override
    public ProductVo getProductById(Integer productId) {
        ProductVo productVo = productApiService.getProductById(productId);
        if (productVo == null) {
            throw new BusinessException("商品未找到");
        }
        List<ProductImageVo> productImageVos = getProductImageById(productVo.getId());
        productVo.setProductImageVos(productImageVos);
        return productVo;
    }

    @Override
    public List<ProductVo> getProductByIds(ProductRequest request) {
        return productApiService.getProductByIds(request);
    }

    @Override
    public boolean addCart(CartRequest request) {
        return productApiService.addCart(request);
    }

}
