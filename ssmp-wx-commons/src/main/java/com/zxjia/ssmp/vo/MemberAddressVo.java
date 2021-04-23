package com.zxjia.ssmp.vo;

import lombok.Data;

@Data
public class MemberAddressVo {

    private int id;

    private String realName;

    private String mobile;

    private String address;

    private String isDefault;

    private Integer memberId;
}
