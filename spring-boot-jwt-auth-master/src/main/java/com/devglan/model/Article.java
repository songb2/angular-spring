package com.devglan.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Article
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties
    private Category category;
    public void setCategory(Category category)
    {
        this.category = category;
    }
    public Category getCategory()
    {
        return category;
    }

    @JsonInclude
    @Transient
    private int categoryId;
    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }
    public int getCategoryId()
    {
        return categoryId;
    }
    private String name;
    private String title;
    private String content;
    @Lob
    private byte[] video;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setVideo(byte[] video)
    {
        this.video = video;
    }
    public byte[] getVideo() {
        return video;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
}