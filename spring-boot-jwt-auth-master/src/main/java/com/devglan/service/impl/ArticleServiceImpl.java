package com.devglan.service.impl;

import com.devglan.dao.ArticleDao;
import com.devglan.model.Article;
import com.devglan.model.ArticleDto;
import com.devglan.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public List<Article> findAll() {
		List<Article> list = new ArrayList<>();
		articleDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		articleDao.deleteById(id);
	}

	@Override
	public Article findOne(String name) {
		return articleDao.findByName(name);
	}

	@Override
	public Article findById(int id) {
		Optional<Article> optionalArticle = articleDao.findById(id);
		return optionalArticle.isPresent() ? optionalArticle.get() : null;
	}

    @Override
    public ArticleDto update(ArticleDto articleDto) {
        Article article = findById(articleDto.getId());
        if(article != null) {
            BeanUtils.copyProperties(articleDto, article);
            articleDao.save(article);
        }
        return articleDto;
    }

    @Override
    public Article save(ArticleDto article) {
	    Article newArticle = new Article();
        newArticle.setName(article.getName());
        newArticle.setCategoryId(article.getCategoryId());
        newArticle.setTitle(article.getTitle());
        newArticle.setContent(article.getContent());
        
        return articleDao.save(newArticle);
    }
}
