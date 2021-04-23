package com.zxjia.ssmp.dto;

import lombok.Data;

@Data
public class MemberRequest {

    private String nickName;

    private String mobile;

    private String message;

    private String template;
}
