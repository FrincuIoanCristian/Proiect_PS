package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News getNews(long id){
        return newsRepository.findById(id).orElse(null);
    }

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public News updateNews(long id, News newDetails){
        News news = newsRepository.findById(id).orElse(null);
        if(news != null){
            news.setCategory(newDetails.getCategory());
            news.setTitle(newDetails.getTitle());
            news.setContent(newDetails.getContent());
            news.setPublishedAt(newDetails.getPublishedAt());
            return newsRepository.save(news);
        }else{
            return null;
        }
    }
    public void deleteNews(long id){
        newsRepository.deleteById(id);
    }
}
