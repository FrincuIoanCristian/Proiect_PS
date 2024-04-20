package com.example.restservice.contract.data;

import com.example.restservice.contract.NewsContract;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsData implements NewsContract {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsData(NewsRepository repository) {
        this.newsRepository = repository;
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findById(long id) {
        return this.newsRepository.findById(id);
    }

    @Override
    public News save(News news) {
        return this.newsRepository.save(news);
    }

    @Override
    public void deleteById(long id) {
        this.newsRepository.deleteById(id);
    }

    @Override
    public List<News> findByCategoryName(String categoryName) {
        return this.newsRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<User> findUsersByNewsId(Long newsId) {
        return this.newsRepository.findUsersByNewsId(newsId);
    }
}
