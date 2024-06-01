package com.example.restservice.contract.data;

import com.example.restservice.contract.NewsContract;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementarea contractului pentru gestionarea datelor stirilor în baza de date.
 */
@Repository
public class NewsData implements NewsContract {
    /**
     * Utilizez repozitory-ul JPA pentru lucrul cu baza de date pentru tabela News
     */
    private final NewsRepository newsRepository;

    /**
     * Constructorul care injectează dependența către NewsRepository.
     *
     * @param newsRepository-ul pentru gestionarea entităților News în baza de date
     */
    @Autowired
    public NewsData(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Metoda care cauta toate stirile
     *
     * @return lista de stiri
     */
    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    /**
     * Metoda care cauta o stire dupa ID
     *
     * @param id ID-ul stirii cautate
     * @return Stirea gasita sau null
     */
    @Override
    public Optional<News> findById(Long id) {
        return this.newsRepository.findById(id);
    }

    /**
     * Metoda care salveaza stirea in baza de date
     *
     * @param news Detaliile stirii ce vreau sa o salvez
     * @return Stirea salvat
     */
    @Override
    public News save(News news) {
        return this.newsRepository.save(news);
    }

    /**
     * Metoda care sterge o stire
     *
     * @param id ID-ul stirii ce urmeaza sa fie stearsa
     */
    @Override
    public void deleteById(Long id) {
        this.newsRepository.deleteById(id);
    }

    /**
     * Metoda care cauta toate stirile dupa numele categoriei din care face parte
     *
     * @param categoryName Numele categoriei
     * @return lista de stiri
     */
    @Override
    public List<News> findByCategoryName(String categoryName) {
        return this.newsRepository.findByCategoryName(categoryName);
    }

    /**
     * Metoda care cauta toti utilizatori ce sunt abonati la categoria de care face parte stirea
     *
     * @param newsId Id-ul stirii
     * @return lista de utilizatori
     */
    @Override
    public List<User> findUsersByNewsId(Long newsId) {
        return this.newsRepository.findUsersByNewsId(newsId);
    }

    @Override
    public List<News> findTop3ByOrderByPublishedAtDesc(Pageable pageable) {
        return this.newsRepository.findTop3ByOrderByPublishedAtDesc(pageable);
    }


}
