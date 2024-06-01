package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clasa care reprezinta entitatea "News" in cadrul aplicatiei.
 * Aceasta clasa este mapata pe o tabela în baza de date si contine informatii despre stirile aplicatiei.
 * Am folosit @Setter si @Getter pentru a nu mai scrie settere-le si gettere-le implicit
 */
@Setter
@Getter
@Entity
@Table(name = "News")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private long newsId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public News() {
    }

    public News(long newsId, Category category, String title, String content, LocalDate publishedAt, String image) {
        this.newsId = newsId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return newsId == news.newsId && Objects.equals(category, news.category) && Objects.equals(title, news.title) && Objects.equals(content, news.content) && Objects.equals(publishedAt, news.publishedAt) && Objects.equals(image, news.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, category, title, content, publishedAt, image);
    }
}
