package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.model.Subscription;
import com.example.restservice.model.User;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(Long id);

    News createNews(News news);

    News updateNews(Long id, News updateNews);

    void deleteNews(Long id);

    List<News> getNewsByCategoryName(String categoryName);

    List<User> getUsersByNewsId(Long newsId);
}
