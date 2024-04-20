package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.NewsContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.observer.EmailService;
import com.example.restservice.observer.UserNotification;
import com.example.restservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsContract newsContract;
    private final CategoryContract categoryContract;
    private final EmailService emailService;


    private final List<UserNotification> observers = new ArrayList<>();

    @Autowired
    public NewsServiceImpl(NewsContract newsContract, CategoryContract categoryContract, EmailService emailService) {
        this.newsContract = newsContract;
        this.categoryContract = categoryContract;
        this.emailService = emailService;
    }

    /**
     * Returnez toate stirile.
     *
     * @return lista cu toate stirile
     */
    @Override
    public List<News> getAllNews() {
        return newsContract.findAll();
    }

    /**
     * Returnez o stire dupa un id
     *
     * @param id id-ul stirii cautate
     * @return stirea gasita cu id-ul respectiv/null
     */
    @Override
    public News getNewsById(long id) {
        return newsContract.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza o stire in tabela
     *
     * @param news detaliile stirii ce doresc sa o creez
     * @return stirea creata
     */
    @Override
    public News createNews(News news) {
        News createdNews = newsContract.save(news);
        createObserver(news);
        notifyObservers(createdNews);
        return createdNews;
    }

    /**
     * Actualizez o stire cu id-ul precizat
     *
     * @param id         id-ul stirii ce doresc sa o modific
     * @param updateNews noile detalii ale stirii
     * @return sterea nou creata si updatata
     */
    @Override
    public News updateNews(long id, News updateNews) {
        News news = newsContract.findById(id).orElse(null);
        if (news != null) {
            updateNews.setNewsId(news.getNewsId());
            News updatedNews = newsContract.save(updateNews);
            notifyObservers(updatedNews);
            return updatedNews;
        } else {
            return null;
        }
    }

    /**
     * Sterge o stire dupa un id
     *
     * @param id id-ul stirii ce doresc sa o sterg
     */
    @Override
    public void deleteNews(long id) {
        newsContract.deleteById(id);
    }

    /**
     * Returneaza toate stirile ce apartin unei categorii cu numele dat
     *
     * @param categoryName numele categoriei pentru care caut stiri
     * @return lista cu stirile gasite
     */
    @Override
    public List<News> getNewsByCategoryName(String categoryName) {
        return newsContract.findByCategoryName(categoryName);
    }

    @Override
    public List<User> getUsersByNewsId(Long newsId) {
        return newsContract.findUsersByNewsId(newsId);
    }

    private void createObserver(News news) {
        observers.clear();
        List<User> users = newsContract.findUsersByNewsId(news.getNewsId());
        for (User user : users) {
            UserNotification userNotification = new UserNotification(user, emailService);
            observers.add(userNotification);
        }
    }

    /**
     * Anunt toti observatori ca am facut o modificare(in cazul nostru, am adaugat/modificat o stire)
     *
     * @param news Stirea nou creata sau updatata
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
