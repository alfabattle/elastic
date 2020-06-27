package com.ashikhman.elastic.controller;

import com.ashikhman.elastic.dto.ArticleDto;
import com.ashikhman.elastic.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/create")
    public void create() {
        service.addSomeArticle();
    }

    @GetMapping
    public List<ArticleDto> articles() {
        return service.getAll();
    }

    @GetMapping("/findByTitle")
    public List<ArticleDto> findByTitle(@Valid @RequestParam String title) {
        return service.getAllByTitle(title);
    }

    @GetMapping("/{id}")
    public ArticleDto article(@Valid @PathVariable String id) {
        return service.getById(id);
    }
}
