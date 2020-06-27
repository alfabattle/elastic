package com.ashikhman.elastic.repository;

import com.ashikhman.elastic.document.PersonDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<PersonDocument, String> {

}
