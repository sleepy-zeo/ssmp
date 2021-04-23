package com.zxjia.ssmp.controller;

import com.zxjia.ssmp.doc.ProductDoc;
import com.zxjia.ssmp.service.ProductElasticsearchService;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ElasticController {

    @Autowired
    private ProductElasticsearchService productElasticsearchService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/es/findAll")
    public Iterable<ProductDoc> findAll() {
        return productElasticsearchService.findAll();
    }

    @RequestMapping("/es/product/{id}")
    @ResponseBody
    public ProductDoc getBookById(@PathVariable long id) {
        Optional<ProductDoc> opt = productElasticsearchService.findById(id);
        ProductDoc book = opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("/es/saveAll")
    public void save() {
        List<ProductVo> productVoList = productService.findAll();

        List<ProductDoc> productDocList = new ArrayList<>();
        productVoList.stream().forEach(e -> {
            ProductDoc productDoc = new ProductDoc();
            BeanUtils.copyProperties(e, productDoc);
            productDocList.add(productDoc);
        });
        productElasticsearchService.saveAll(productDocList);
    }

}