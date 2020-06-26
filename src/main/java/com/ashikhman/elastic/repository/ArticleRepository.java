package com.ashikhman.elastic.repository;

import com.ashikhman.elastic.document.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, String> {

    Optional<ArticleDocument> findByTitle(String title);
}
