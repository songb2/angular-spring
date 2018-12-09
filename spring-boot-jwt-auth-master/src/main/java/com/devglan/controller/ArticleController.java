package com.devglan.controller;

import com.devglan.exception.*;
import com.devglan.model.ApiResponse;
import com.devglan.model.Article;
import com.devglan.model.ArticleDto;
import com.devglan.service.ArticleService;
import com.devglan.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    ArticleDao articleRepository;

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().iterator().forEachRemaining(list::add);
        
		return list;
    }

    @PostMapping("/articles")
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable(value = "id") int articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable(value = "id") int articleId,
                                           @Valid @RequestBody Article articleDetails) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());

        Article updatedArticle = articleRepository.save(article);
        return updatedArticle;
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "id") int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        articleRepository.delete(article);

        return ResponseEntity.ok().build();
    }
}