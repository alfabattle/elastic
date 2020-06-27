package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "loans")
@Data
@Accessors(chain = true)
public class LoanDocument {

    @Id
    private String id;

    private String docId;

    private Long amount;

    private String startDate;

    private Integer period;
}
