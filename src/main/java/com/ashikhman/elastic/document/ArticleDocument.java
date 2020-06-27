package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "articles")
@Data
@Accessors(chain = true)
public class ArticleDocument {

    @Id
    private String id;

    private String title;
}
