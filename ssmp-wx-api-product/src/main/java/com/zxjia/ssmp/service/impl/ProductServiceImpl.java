package com.zxjia.ssmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.dto.ProductRequest;
import com.zxjia.ssmp.entity.Product;
import com.zxjia.ssmp.entity.ProductImage;
import com.zxjia.ssmp.mapper.ProductImageMapper;
import com.zxjia.ssmp.mapper.ProductMapper;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.ProductImageVo;
import com.zxjia.ssmp.vo.ProductVo;
import com.zxjia.ssmp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public List<ProductVo> getProductByCateId(Integer catId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_cate_id", catId);

        List<ProductVo> productVos = new ArrayList<>();

        List<Product> productList = this.list(queryWrapper);

        productList.stream().forEach(product -> {
            ProductVo productVo = new ProductVo();
            product.setImgUrl(ResultVO.getImages() + product.getImgUrl());
            BeanUtils.copyProperties(product, productVo);
            productVos.add(productVo);
        });
        return productVos;
    }

    @Override
    public List<ProductImageVo> getProductImageById(Integer productId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);

        List<ProductImageVo> productImageVos = new ArrayList<>();
        List<ProductImage> productImages = productImageMapper.selectList(queryWrapper);
        productImages.stream().forEach((e) -> {
            ProductImageVo productImageVo = new ProductImageVo();
            productImageVo.setImgUrl(ResultVO.getImages() + e.getImage());
            productImageVos.add(productImageVo);
        });
        return productImageVos;
    }

    @Override
    public ProductVo getProductById(Integer productId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", productId);

        Product product = this.getOne(queryWrapper);
        ProductVo productVo = new ProductVo();

        if (StringUtils.isNotEmpty(product.getImgUrl())) {
            product.setImgUrl(ResultVO.getImages() + product.getImgUrl());
        }
        BeanUtils.copyProperties(product, productVo);

        return productVo;
    }

    @Override
    public List<ProductVo> getProductByIds(ProductRequest request) {
        log.info("商品ids：" + request.getProductIds());
        List<Product> productVos = this.list(new QueryWrapper<Product>().lambda().in(Product::getId, request.getProductIds()));
        return getProductVoList(productVos);
    }

    @Override
    public List<ProductVo> findAll() {
        List<Product> productVos = this.list();

        return getProductVoList(productVos);
    }

    private List<ProductVo> getProductVoList(List<Product> productVos) {
        List<ProductVo> productVoList = new ArrayList<>();

        productVos.stream().forEach(e -> {
            ProductVo productVo = new ProductVo();
            e.setImgUrl(ResultVO.getImages() + e.getImgUrl());
            BeanUtils.copyProperties(e, productVo);
            productVoList.add(productVo);
        });
        return productVoList;
    }
}
