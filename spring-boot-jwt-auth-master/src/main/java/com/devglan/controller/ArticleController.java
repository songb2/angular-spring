package com.devglan.controller;

import com.devglan.exception.*;
import com.devglan.model.ApiResponse;
import com.devglan.model.Article;
import com.devglan.model.ArticleDto;
import com.devglan.service.ArticleService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devglan.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import javax.validation.Valid;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    ArticleDao articleRepository;

    @GetMapping("/articles")
    public ApiResponse<List<Article>> getAllArticles() {
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().iterator().forEachRemaining(list::add);
        
		return new ApiResponse<>(HttpStatus.OK.value(), "Article list fetched successfully.",list);
    }

    // @PostMapping("/articles")
    // public Article createArticle(@Valid @RequestBody Article article) {
    //     return articleRepository.save(article);
    // }

    @PostMapping("/articles")
    public Article createArticle(@RequestPart("video") MultipartFile video,
     @RequestPart("article") String articleString) throws JsonParseException, JsonMappingException, IOException{
        Article article = new ObjectMapper().readValue(articleString, Article.class);
        article.setName(video.getOriginalFilename()); 
        article.setVideo(video.getBytes());
        return articleRepository.save(article);
    }

    @GetMapping("/articles/{id}")
    public ApiResponse<Article> getArticleById(@PathVariable(value = "id") int articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));
        return new ApiResponse<>(HttpStatus.OK.value(), "Get article successfully", article);
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