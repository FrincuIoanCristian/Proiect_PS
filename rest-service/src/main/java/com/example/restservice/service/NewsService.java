package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private final NewsRepository newsRepository;
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    public News getNews(long id){
        return newsRepository.findById(id).orElse(null);
    }

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public News updateNews(long id, News updateNews){
        News news = newsRepository.findById(id).orElse(null);
        if(news != null){
            updateNews.setNewsId(news.getNewsId());
            return newsRepository.save(updateNews);
        }else{
            return null;
        }
    }
    public void deleteNews(long id){
        newsRepository.deleteById(id);
    }
}
