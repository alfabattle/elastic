package com.ashikhman.elastic.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {

    private String id;

    private String title;

    private List<AuthorDto> authors = new ArrayList<>();

    public ArticleDto addAuthor(AuthorDto author) {
        authors.add(author);

        return this;
    }
}
