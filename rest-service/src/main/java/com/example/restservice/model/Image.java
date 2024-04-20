package com.example.restservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long imageId;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "url", columnDefinition = "TEXT")
    private String url;

    public Image() {
    }

    public Image(long imageId, News news, String url) {
        this.imageId = imageId;
        this.news = news;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return imageId == image.imageId && Objects.equals(news, image.news) && Objects.equals(url, image.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, news, url);
    }
}
