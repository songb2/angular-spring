package com.devglan.service;

import com.devglan.model.Article;
import com.devglan.model.ArticleDto;

import java.util.List;

public interface ArticleService {

    Article save(ArticleDto user);

    List<Article> findAll();

    void delete(int id);

    Article findOne(String name);

    Article findById(int id);

    ArticleDto update(ArticleDto userDto);
}
