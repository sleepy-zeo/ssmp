package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("member_address")
@Data
public class MemberAddress {

    @TableId(type = IdType.AUTO)
    private int id;

    private String realName;

    private String mobile;

    private String address;

    private String isDefault;

    private Integer memberId;
}
