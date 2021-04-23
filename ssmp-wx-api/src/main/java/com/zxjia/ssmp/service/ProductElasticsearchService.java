package com.zxjia.ssmp.service;

import com.zxjia.ssmp.doc.ProductDoc;

import java.util.List;
import java.util.Optional;

public interface ProductElasticsearchService {

    Optional<ProductDoc> findById(long id);

    ProductDoc save(ProductDoc product);

    Iterable<ProductDoc> saveAll(Iterable<ProductDoc> product);

    void delete(ProductDoc product);

    Iterable<ProductDoc> findAll();

    List<ProductDoc> findByProductName(String productName);
}
