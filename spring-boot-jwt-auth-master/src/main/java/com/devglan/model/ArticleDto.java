package com.devglan.model;

import java.util.Date;

public class ArticleDto
{
    private int id;
    private int categoryId;
    private String name;
    private String title;
    private String content;
    // private Date createdAt;
    // private Date updatedAt;

    public int getId() {
        return id;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // public Date getCreatedAt() {
    //     return createdAt;
    // }

    // public Date getUpdatedAt() {
    //     return updatedAt;
    // }
}