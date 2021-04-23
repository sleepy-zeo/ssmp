package com.zxjia.ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxjia.ssmp.entity.ProductCate;
import com.zxjia.ssmp.vo.ProductCateVo;

import java.util.List;

public interface ProductCateService extends IService<ProductCate> {

    List<ProductCateVo> getProductCate();
}
