package com.zxjia.ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxjia.ssmp.entity.Member;
import com.zxjia.ssmp.entity.MemberAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<MemberAddress> {

    @Select("select * from member where id=#{memberId}")
    Member getMemberById(Integer memberId);

    @Select("select * from member where mobile=#{mobile}")
    Member getMemberByMobile(String mobile);
}
