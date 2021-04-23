package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.dto.IndexRequest;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.IndexVo;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "首页管理")
@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "查询商品类别信息")
    @PostMapping(value = "/index/getProductByCateId")
    public ResultVO<IndexVo> getProductByCateId(@RequestBody IndexRequest request) {
        return ResultVO.success(productService.getProductCateById(request.getCatId()));
    }

    @ApiOperation(value = "搜索")
    @PostMapping(value = "/index/search")
    public ResultVO<IndexVo> search(@RequestBody IndexRequest request) {
        return ResultVO.success(productService.search(request));
    }

}
