package com.example.restservice.repository;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfata care defineste operatiile de baza pentru gestionarea entitatilor News in baza de date.
 * Extinde JpaRepository, oferind astfel metodele necesare pentru a accesa si manipula datele entitaților News.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    /**
     * Metoda ce obtine lista de stiri ce apartin unei categorii cu un anumit nume
     * @param categoryName Numele categoriei
     * @return lista de stiri
     */
    @Query("SELECT n FROM News n WHERE n.category.categoryName = :categoryName")
    List<News> findByCategoryName(String categoryName);

    /**
     * Metoda ce obtine toti utilizatori ce sunt abonati la categoria din care face parte stirea cu un ID
     * @param newsId ID-ul stirii
     * @return lista de utilizatori
     */
    @Query("SELECT DISTINCT u FROM User u " +
            "INNER JOIN u.subscriptions s " +
            "INNER JOIN s.category c " +
            "INNER JOIN c.newsList n " +
            "WHERE n.newsId = :newsId")
    List<User> findUsersByNewsId(Long newsId);

    @Query(value = "SELECT n FROM News n ORDER BY n.publishedAt DESC")
    List<News> findTop3ByOrderByPublishedAtDesc(Pageable pageable);
}
