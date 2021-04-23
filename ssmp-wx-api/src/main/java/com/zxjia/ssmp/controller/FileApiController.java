package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.service.QCloudService;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/entry/file")
public class FileApiController {

    @Autowired
    QCloudService qCloudService;

    @ApiImplicitParam(name = "file", value = "文件", required = true)
    @ApiOperation(value = "上传文件并返回对应url")
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        return ResultVO.success(qCloudService.upload(file), "上传完成");
    }

    @ApiImplicitParam(name = "fileName", value = "文件名", required = true)
    @ApiOperation(value = "获取文件的临时rul")
    @GetMapping("/link")
    public ResultVO parsed(String fileName) {
        System.out.println(qCloudService.getTemporaryPresignedUrl(fileName));
        return ResultVO.success(qCloudService.getTemporaryPresignedUrl(fileName), "获取临时链接完成");
    }
}
