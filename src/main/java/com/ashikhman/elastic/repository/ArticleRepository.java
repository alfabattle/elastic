package com.ashikhman.elastic.repository;

import com.ashikhman.elastic.document.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, String> {

    Iterable<ArticleDocument> findByTitle(String title);
}
