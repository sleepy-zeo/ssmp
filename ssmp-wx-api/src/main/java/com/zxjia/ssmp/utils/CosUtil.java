package com.zxjia.ssmp.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.zxjia.ssmp.config.CosProperties;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

public class CosUtil {

    public static void putObject(COSClient cosClient, CosProperties cosProperties, byte[] bytes, String key) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        cosClient.putObject(cosProperties.getBucketName(), key, new ByteArrayInputStream(bytes), objectMetadata);
    }

    public static String getTemporaryPresignedUrl(COSClient cosClient, CosProperties cosProperties, String key) {
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(cosProperties.getBucketName(), key, HttpMethodName.GET);

        // 设置签名url过期时间，默认为一小时
        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 30 * 1000L);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        return url.toString();
    }
}
