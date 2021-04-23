package com.zxjia.ssmp.service.impl;

import com.zxjia.ssmp.doc.ProductDoc;
import com.zxjia.ssmp.mapper.ProductElasticsearchRepository;
import com.zxjia.ssmp.service.ProductElasticsearchService;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductElasticsearchServiceImpl implements ProductElasticsearchService {

    @Autowired
    ProductElasticsearchRepository productElasticsearchRepository;

    @Override
    public Optional<ProductDoc> findById(long id) {
        return productElasticsearchRepository.findById(id);
    }

    @Override
    public ProductDoc save(ProductDoc product) {
        return productElasticsearchRepository.save(product);
    }

    @Override
    public Iterable<ProductDoc> saveAll(Iterable<ProductDoc> product) {
        return productElasticsearchRepository.saveAll(product);
    }

    @Override
    public void delete(ProductDoc product) {
        productElasticsearchRepository.delete(product);
    }

    @Override
    public Iterable<ProductDoc> findAll() {
        return productElasticsearchRepository.findAll();
    }

    @Override
    public List<ProductDoc> findByProductName(String productName) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(productName);

        Iterable<ProductDoc> searchResult = productElasticsearchRepository.search(builder);
        Iterator<ProductDoc> iterator = searchResult.iterator();
        List<ProductDoc> productDocList = new ArrayList<>();
        while (iterator.hasNext()) {
            productDocList.add(iterator.next());
        }
        return productDocList;
    }
}
