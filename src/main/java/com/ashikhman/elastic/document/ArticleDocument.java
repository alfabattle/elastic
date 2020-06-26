package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * Article data object.
 */
@Document(indexName = "blog")
@Data
@Accessors(chain = true)
public class ArticleDocument {

    /**
     * Article unique identifier.
     */
    @Id
    private String id;

    /**
     * The title.
     */
    private String title;

    /**
     * Article authors.
     */
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<AuthorDocument> authors;

    /**
     * Adds given author.
     *
     * @param author author data
     * @return self
     */
    public ArticleDocument addAuthor(AuthorDocument author) {
        if (null == authors) {
            authors = new ArrayList<>();
        }

        authors.add(author);

        return this;
    }
}
