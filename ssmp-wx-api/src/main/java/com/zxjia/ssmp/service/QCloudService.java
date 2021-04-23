package com.zxjia.ssmp.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QCloudService {

    List<String> upload(MultipartFile file);

    String getTemporaryPresignedUrl(String fileName);
}
