package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "authors")
@Data
@Accessors(chain = true)
public class AuthorDocument {

    private String id;

    private String fullName;

    private String articleId;
}
