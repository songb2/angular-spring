package com.devglan.dao;

import com.devglan.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends CrudRepository<Article, Integer> {

    Article findByName(String name);
}
