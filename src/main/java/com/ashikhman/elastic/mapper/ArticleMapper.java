package com.ashikhman.elastic.mapper;

import com.ashikhman.elastic.document.ArticleDocument;
import com.ashikhman.elastic.document.AuthorDocument;
import com.ashikhman.elastic.dto.ArticleDto;
import com.ashikhman.elastic.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto articleToDto(ArticleDocument entity);

    AuthorDto authorToDto(AuthorDocument entity);
}
