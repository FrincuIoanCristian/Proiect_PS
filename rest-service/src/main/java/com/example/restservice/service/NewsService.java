package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interfața care definește operațiile disponibile pentru gestionarea știrilor în sistem.
 * Aceste operații includ obținerea tuturor știrilor, obținerea unei știri după ID,
 * crearea, actualizarea și ștergerea știrilor, precum și obținerea știrilor asociate unei anumite categorii
 * și a utilizatorilor care au abonamente pentru o anumită știre.
 */
public interface NewsService {
    /**
     * Obține o listă cu toate știrile din sistem.
     *
     * @return Lista cu știri
     */
    List<News> getAllNews();

    /**
     * Obține o știre după ID-ul specificat.
     *
     * @param id ID-ul știrii căutate
     * @return Știrea cu ID-ul specificat sau null dacă nu există
     */
    News getNewsById(Long id);

    /**
     * Creează o știre nouă în sistem.
     *
     * @param news Știrea care urmează să fie creată
     * @return Știrea creată
     */
    News createNews(News news);

    /**
     * Actualizează o știre existentă în sistem.
     *
     * @param id         ID-ul știrii care urmează să fie actualizată
     * @param updateNews Știrea actualizată
     * @return Știrea actualizată sau null dacă nu există o știre cu ID-ul specificat
     */
    News updateNews(Long id, News updateNews);

    /**
     * Șterge o știre existentă din sistem.
     *
     * @param id ID-ul știrii care urmează să fie ștearsă
     */
    void deleteNews(Long id);

    /**
     * Obține o listă cu știrile asociate unei anumite categorii.
     *
     * @param categoryName Numele categoriei pentru care se caută știrile
     * @return Lista cu știrile asociate categoriei specificate
     */
    List<News> getNewsByCategoryName(String categoryName);

    /**
     * Obține o listă cu utilizatorii care au abonamente pentru o anumită știre.
     *
     * @param newsId ID-ul știrii pentru care se caută utilizatorii cu abonamente
     * @return Lista cu utilizatorii care au abonamente pentru știrea specificată
     */
    List<User> getUsersByNewsId(Long newsId);

    List<News> findTop3ByOrderByPublishedAtDesc(Pageable pageable);
}
