package com.ashikhman.elastic.service;

import com.ashikhman.elastic.document.ArticleDocument;
import com.ashikhman.elastic.document.AuthorDocument;
import com.ashikhman.elastic.dto.ArticleDto;
import com.ashikhman.elastic.mapper.ArticleMapper;
import com.ashikhman.elastic.repository.ArticleRepository;
import com.ashikhman.elastic.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ElasticsearchOperations elasticsearch;
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final ArticleMapper mapper;

    public void addSomeArticle() {
        var article = new ArticleDocument()
                .setId(UUID.randomUUID().toString())
                .setTitle("Some title");

        elasticsearch.save(article);

        Arrays.asList(createSomeAuthor("Author1"), createSomeAuthor("Author2"))
                .forEach(author -> {
                    author.setArticleId(article.getId());
                    authorRepository.save(author);
                });
    }

    public List<ArticleDto> getAll() {
        return StreamSupport.stream(articleRepository.findAll().spliterator(), false)
                .map(mapper::articleToDto)
                .collect(Collectors.toList());
    }

    public ArticleDto getById(String id) {
        return articleRepository.findById(id)
                .map(mapper::articleToDto)
                .map(articleDto -> {
                    authorRepository.findByArticleId(id).forEach(authorDocument -> {
                        articleDto.addAuthor(mapper.authorToDto(authorDocument));
                    });

                    return articleDto;
                })
                .orElseThrow(() -> new RuntimeException(String.format("Article with id `%s` not found", id)));
    }

    public List<ArticleDto> getAllByTitle(String title) {
        return StreamSupport.stream(articleRepository.findByTitle(title).spliterator(), false)
                .map(mapper::articleToDto)
                .collect(Collectors.toList());
    }

    private AuthorDocument createSomeAuthor(String name) {
        return new AuthorDocument()
                .setId(UUID.randomUUID().toString())
                .setFullName(name);
    }
}
