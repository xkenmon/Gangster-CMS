package com.ganster.cms.search.repository;

import com.ganster.cms.search.model.ArticleModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//泛型的参数分别是实体类型和主键类型
public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleModel, Integer> {
}
