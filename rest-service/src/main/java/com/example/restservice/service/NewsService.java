package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.observer.NewsObserver;
import com.example.restservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private final NewsRepository newsRepository;
    private List<NewsObserver> observers = new ArrayList<>();
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    public News getNewsById(long id){
        return newsRepository.findById(id).orElse(null);
    }

    public News createNews(News news){
        News createdNews = newsRepository.save(news);
        notifyObservers(createdNews);
        return createdNews;
    }

    public News updateNews(long id, News updateNews){
        News news = newsRepository.findById(id).orElse(null);
        if(news != null){
            updateNews.setNewsId(news.getNewsId());
            News updatedNews = newsRepository.save(updateNews);
            notifyObservers(updatedNews);
            return updatedNews;
        }else{
            return null;
        }
    }
    public void deleteNews(long id){
        newsRepository.deleteById(id);
    }

    public List<News> getNewsByCategoryName(String categoryName) {
        return newsRepository.findByCategoryName(categoryName);
    }

    public void addObserver(NewsObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NewsObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(News news) {
        for (NewsObserver observer : observers) {
            observer.update(news);
        }
    }
}
