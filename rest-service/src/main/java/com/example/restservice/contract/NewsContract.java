package com.example.restservice.contract;

import com.example.restservice.model.News;
import com.example.restservice.model.User;

import java.util.List;
import java.util.Optional;

public interface NewsContract {
    List<News> findAll();

    Optional<News> findById(long id);

    News save(News news);

    void deleteById(long id);

    List<News> findByCategoryName(String categoryName);

    List<User> findUsersByNewsId(Long newsId);
}
