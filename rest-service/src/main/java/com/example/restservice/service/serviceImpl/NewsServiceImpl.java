package com.example.restservice.service.serviceImpl;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.repository.NewsRepository;
import com.example.restservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private final NewsRepository newsRepository;
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Returnez toate stirile.
     * @return lista cu toate stirile
     */
    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * Returnez o stire dupa un id
     * @param id id-ul stirii cautate
     * @return stirea gasita cu id-ul respectiv/null
     */
    @Override
    public News getNewsById(long id){
        return newsRepository.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza o stire in tabela
     * @param news detaliile stirii ce doresc sa o creez
     * @return stirea creata
     */
    @Override
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
    @Override
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
    @Override
    public void deleteNews(long id){
        newsRepository.deleteById(id);
    }

    /**
     * Returneaza toate stirile ce apartin unei categorii cu numele dat
     * @param categoryName numele categoriei pentru care caut stiri
     * @return lista cu stirile gasite
     */
    @Override
    public List<News> getNewsByCategoryName(String categoryName) {
        return newsRepository.findByCategoryName(categoryName);
    }
    @Override
    public List<User> getUsersByNewsId(Long newsId){
        return newsRepository.findUsersByNewsId(newsId);
    }

    /**
     * Anunt toti observatori ca am facut o modificare(in cazul nostru, am adaugat/modificat o stire)
     * @param news Stirea nou creata sau updatata
     */
    private void notifyObservers(News news) {
        List<User> users = getUsersByNewsId(news.getNewsId());
        for (User user:users) {
            user.update(news);
        }
    }
}
