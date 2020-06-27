package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "persons")
@Data
@Accessors(chain = true)
public class PersonDocument {

    @Id
    private String id;

    private String docId;

    private String fio;

    private String birthday;

    private Long salary;

    private String gender;
}
