package com.zxjia.ssmp.mapper;

import com.zxjia.ssmp.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDoc, Long> {

}
