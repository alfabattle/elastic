package com.ashikhman.elastic.repository;

import com.ashikhman.elastic.document.LoanDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LoanRepository extends ElasticsearchRepository<LoanDocument, String> {

}
