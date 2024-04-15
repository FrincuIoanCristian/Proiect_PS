package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.model.Subscription;
import com.example.restservice.model.User;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(long id);
    News createNews(News news);
    News updateNews(long id, News updateNews);
    void deleteNews(long id);
    List<News> getNewsByCategoryName(String categoryName);
    List<User> getUsersByNewsId(Long newsId);
}
