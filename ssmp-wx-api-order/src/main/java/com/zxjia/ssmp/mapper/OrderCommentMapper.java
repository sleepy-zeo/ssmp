package com.zxjia.ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxjia.ssmp.entity.OrderComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderCommentMapper extends BaseMapper<OrderComment> {
}
