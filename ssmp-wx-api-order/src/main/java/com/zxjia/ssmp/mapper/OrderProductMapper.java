package com.zxjia.ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxjia.ssmp.entity.OrdersProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderProductMapper extends BaseMapper<OrdersProduct> {
}
