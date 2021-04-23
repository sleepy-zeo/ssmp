package com.zxjia.ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxjia.ssmp.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Orders> {
}
