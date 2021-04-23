package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("member")
@Data
public class Member {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nickName;

    private String mobile;

    private String gender;

    private String source;
}
