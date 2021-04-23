package com.zxjia.ssmp.service.impl;

import cn.hutool.core.date.DateUtil;
import com.qcloud.cos.COSClient;
import com.zxjia.ssmp.config.CosProperties;
import com.zxjia.ssmp.service.QCloudService;
import com.zxjia.ssmp.utils.CosUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class QCloudServiceImpl implements QCloudService {

    @Autowired
    private COSClient cosClient;
    @Autowired
    private CosProperties cosProperties;

    public List<String> upload(MultipartFile file) {
        List<String> urls = new ArrayList<>();
        if (file != null) {
            try {
                String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
                String originalFilename = file.getOriginalFilename();

                String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                String fileName = "/user/" + format + "." + fileType;
                CosUtil.putObject(cosClient, cosProperties, file.getBytes(), fileName);
                urls.add(fileName);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return urls;
    }

    @Override
    public String getTemporaryPresignedUrl(String fileName) {
        return CosUtil.getTemporaryPresignedUrl(cosClient, cosProperties, fileName);
    }
}
