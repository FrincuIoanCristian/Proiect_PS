package com.example.restservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsId;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date publishedAt;

    public News(){}

    public News(long newsId, Category category, String title, String content, String image, Date publishedAt) {
        this.newsId = newsId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.image = image;
        this.publishedAt = publishedAt;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
