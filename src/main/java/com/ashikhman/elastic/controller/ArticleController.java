package com.ashikhman.elastic.controller;

import com.ashikhman.elastic.document.ArticleDocument;
import com.ashikhman.elastic.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/aga")
    public List<ArticleDocument> aga() {
        service.addCustomAuthor();

        return service.getAll();
    }
}
