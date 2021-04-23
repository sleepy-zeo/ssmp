package com.zxjia.ssmp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cos")
public class CosProperties {

    private String appId;
    private String secretId;
    private String secretKey;
    private String bucketName;
    private String region;
    private String domain;

}
