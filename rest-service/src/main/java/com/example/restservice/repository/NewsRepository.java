package com.example.restservice.repository;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n WHERE n.category.categoryName = :categoryName")
    List<News> findByCategoryName(String categoryName);

    @Query("SELECT DISTINCT u FROM User u " +
            "INNER JOIN u.subscriptions s " +
            "INNER JOIN s.category c " +
            "INNER JOIN c.newsList n " +
            "WHERE n.newsId = :newsId")
    List<User> findUsersByNewsId(long newsId);
}
