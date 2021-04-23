package com.zxjia.ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.entity.ProductCate;
import com.zxjia.ssmp.mapper.ProductCateMapper;
import com.zxjia.ssmp.service.ProductCateService;
import com.zxjia.ssmp.vo.ProductCateVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCateServiceImpl extends ServiceImpl<ProductCateMapper, ProductCate> implements ProductCateService {

    @Override
    public List<ProductCateVo> getProductCate() {
        List<ProductCateVo> productCateVos = new ArrayList<>();
        this.list().stream().forEach((e) -> {
            ProductCateVo vo = new ProductCateVo();
            vo.setCatId(e.getId());
            vo.setCatName(e.getCatName());
            productCateVos.add(vo);
        });
        return productCateVos;
    }
}
