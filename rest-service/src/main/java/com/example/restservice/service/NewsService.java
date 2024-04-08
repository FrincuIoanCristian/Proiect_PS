package com.example.restservice.service;

import com.example.restservice.model.News;
import com.example.restservice.observer.ConsoleNewsNotifier;
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
    public NewsService(NewsRepository newsRepository, ConsoleNewsNotifier consoleNewsNotifier) {
        this.newsRepository = newsRepository;
        this.observers.add(consoleNewsNotifier);
    }

    /**
     * Returnez toate stirile.
     * @return lista cu toate stirile
     */
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * Returnez o stire dupa un id
     * @param id id-ul stirii cautate
     * @return stirea gasita cu id-ul respectiv/null
     */
    public News getNewsById(long id){
        return newsRepository.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza o stire in tabela
     * @param news detaliile stirii ce doresc sa o creez
     * @return stirea creata
     */
    public News createNews(News news){
        News createdNews = newsRepository.save(news);
        notifyObservers(createdNews);
        return createdNews;
    }

    /**
     * Actualizez o stire cu id-ul precizat
     * @param id id-ul stirii ce doresc sa o modific
     * @param updateNews noile detalii ale stirii
     * @return sterea nou creata si updatata
     */
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

    /**
     * Sterge o stire dupa un id
     * @param id id-ul stirii ce doresc sa o sterg
     */
    public void deleteNews(long id){
        newsRepository.deleteById(id);
    }

    /**
     * Returneaza toate stirile ce apartin unei categorii cu numele dat
     * @param categoryName numele categoriei pentru care caut stiri
     * @return lista cu stirile gasite
     */
    public List<News> getNewsByCategoryName(String categoryName) {
        return newsRepository.findByCategoryName(categoryName);
    }

    /**
     * Adauga un nou observator in lista
     * @param observer noul observator
     */
    public void addObserver(NewsObserver observer) {
        observers.add(observer);
    }

    /**
     * Sterge un observator din lista
     * @param observer observatorul ce vreau sa il sterg
     */
    public void removeObserver(NewsObserver observer) {
        observers.remove(observer);
    }

    /**
     * Anunt toti observatori ca am facut o modificare(in cazul nostru, am adaugat/modificat o stire)
     * @param news Stirea nou creata sau updatata
     */
    private void notifyObservers(News news) {
        for (NewsObserver observer : observers) {
            observer.update(news);
        }
    }
}
