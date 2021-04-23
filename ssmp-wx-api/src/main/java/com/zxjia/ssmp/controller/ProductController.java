package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.ProductCateVo;
import com.zxjia.ssmp.vo.ProductVo;
import com.zxjia.ssmp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品信息管理")
@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "查询商品类别信息")
    @PostMapping(value = "/product/getProductCate")
    public ResultVO<List<ProductCateVo>> getProductCate() {
        return ResultVO.success(productService.getProductCate());
    }

    @ApiImplicitParam(name = "catId", value = "类别Id", required = true)
    @ApiOperation(value = "查询商品类别信息")
    @PostMapping(value = "/product/getProductByCateId")
    public ResultVO<List<ProductVo>> getProductByCateId(Integer catId) {
        return ResultVO.success(productService.getProductByCateId(catId));
    }

    @ApiImplicitParam(name = "productId", value = "商品id", required = true)
    @ApiOperation(value = "商品详情")
    @GetMapping(value = "/product/productDetails")
    public ResultVO<ProductVo> productDetails(Integer productId) {
        return ResultVO.success(productService.getProductById(productId));
    }
}
