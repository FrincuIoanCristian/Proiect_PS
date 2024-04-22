package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.NewsContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.observer.UserNotification;
import com.example.restservice.observer.EmailService;
import com.example.restservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementarea contractului pentru gestionarea datelor stirilor în baza de date.
 */
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsContract newsContract;
    private final CategoryContract categoryContract;
    private final EmailService emailService;

    /**
     * Reprezinta lista de observatori
     */
    private final List<UserNotification> observers = new ArrayList<>();

    /**
     * Constructorul care injectează dependența către NewsContract, CategoryContract si EmailService
     *
     * @param newsContract     Contractul pentru gestionarea datelor de tipul News
     * @param categoryContract Contractul pentru gestionarea datelor de tipul Category
     * @param emailService     Dependinta catre service-ul email-ului
     */
    @Autowired
    public NewsServiceImpl(NewsContract newsContract, CategoryContract categoryContract, EmailService emailService) {
        this.newsContract = newsContract;
        this.categoryContract = categoryContract;
        this.emailService = emailService;
    }

    /**
     * Metoda care obține o listă cu toate știrile din sistem.
     *
     * @return Lista cu știri
     */
    @Override
    public List<News> getAllNews() {
        return newsContract.findAll();
    }

    /**
     * Metoda care obține o știre după ID-ul specificat.
     *
     * @param id ID-ul știrii căutate
     * @return Știrea cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public News getNewsById(Long id) {
        return newsContract.findById(id).orElse(null);
    }

    /**
     * Metoda care creează o știre nouă în sistem.
     *
     * @param news Știrea care urmează să fie creată
     * @return Știrea creată
     */
    @Override
    public News createNews(News news) {
        News createdNews = newsContract.save(news);
        createObserver(news);
        notifyObservers(createdNews);
        return createdNews;
    }

    /**
     * Metoda care actualizează o știre existentă în sistem.
     *
     * @param id         ID-ul știrii care urmează să fie actualizată
     * @param updateNews Știrea actualizată
     * @return Știrea actualizată sau null dacă nu există o știre cu ID-ul specificat
     */
    @Override
    public News updateNews(Long id, News updateNews) {
        News news = newsContract.findById(id).orElse(null);
        if (news != null) {
            updateNews.setNewsId(news.getNewsId());
            return newsContract.save(updateNews);
        } else {
            return null;
        }
    }

    /**
     * Metoda care sterge o știre existentă din sistem.
     *
     * @param id ID-ul știrii care urmează să fie ștearsă
     */
    @Override
    public void deleteNews(Long id) {
        newsContract.deleteById(id);
    }

    /**
     * Metoda care obține o listă cu știrile asociate unei anumite categorii.
     *
     * @param categoryName Numele categoriei pentru care se caută știrile
     * @return Lista cu știrile asociate categoriei specificate
     */
    @Override
    public List<News> getNewsByCategoryName(String categoryName) {
        return newsContract.findByCategoryName(categoryName);
    }

    /**
     * Metoda care obține o listă cu utilizatorii care au abonamente pentru o anumită știre.
     *
     * @param newsId ID-ul știrii pentru care se caută utilizatorii cu abonamente
     * @return Lista cu utilizatorii care au abonamente pentru știrea specificată
     */
    @Override
    public List<User> getUsersByNewsId(Long newsId) {
        return newsContract.findUsersByNewsId(newsId);
    }

    /**
     * Metoda care creaza observatori ce trebuie notificati pentru ca s-a adaugat stire noua
     *
     * @param news stirea nou adaugata
     */
    private void createObserver(News news) {
        observers.clear();
        List<User> users = newsContract.findUsersByNewsId(news.getNewsId());
        for (User user : users) {
            UserNotification userNotification = new UserNotification(user, emailService);
            observers.add(userNotification);
        }
    }

    /**
     * Metoda care anunt toti observatori ca am facut o modificare(in cazul nostru, am adaugat o stire)
     *
     * @param news Stirea nou creata
     */
    private void notifyObservers(News news) {
        Category category = categoryContract.findById(news.getCategory().getCategoryId()).orElse(null);
        for (UserNotification userNotification : observers) {
            System.out.printf(userNotification.getUser().getUsername());
            assert category != null;
            userNotification.update(news, category);
        }
    }
}
