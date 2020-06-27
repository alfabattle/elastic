package com.ashikhman.elastic.repository;

import com.ashikhman.elastic.document.AuthorDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuthorRepository extends ElasticsearchRepository<AuthorDocument, String> {

    Iterable<AuthorDocument> findByArticleId(String id);
}
