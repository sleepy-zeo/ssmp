package com.zxjia.ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxjia.ssmp.entity.ProductCate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductCateMapper extends BaseMapper<ProductCate> {
}
