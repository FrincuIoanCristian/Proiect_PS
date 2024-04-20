package com.example.restservice.controller;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Obtin toate stirile
     *
     * @return o lisat cu toate stirile
     */
    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    /**
     * Obtin o stire dupa un id
     *
     * @param id id-ul stirii cautate
     * @return Un obiect ResponseEntity care contine stirea si statusul HTTP corespunzator.
     */
    @GetMapping("/{id}")
    public ResponseEntity<News> getNews(@PathVariable long id) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza o stire noua
     *
     * @param news detaliile noi stiri
     * @return Un obiect ResponseEntity care contine stirea si statusul HTTP corespunzator.
     */
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza o stire dupa un id
     *
     * @param id         id-ul stirii ce doresc sa o actualizez
     * @param newDetails noile detalii ale stirii
     * @return Un obiect ResponseEntity care contine stirea si statusul HTTP corespunzator.
     */
    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable long id, @RequestBody News newDetails) {
        News updateNews = newsService.updateNews(id, newDetails);
        if (updateNews != null) {
            return new ResponseEntity<>(updateNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge o stire din tabela dupa un id
     *
     * @param id id-ul stirii cautate
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteNews(@PathVariable long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Genereaza toate stirile ce apartin unei categorii cu numele indicat
     *
     * @param categoryName numele categoriei pentru care se cauta stiri
     * @return o lista cu stirile gasite
     */
    @GetMapping("/getNewsByCategoryName")
    public List<News> getNewsByCategoryName(@RequestParam String categoryName) {
        return newsService.getNewsByCategoryName(categoryName);
    }

    @GetMapping("/getUsersByNewsId/{id}")
    public List<User> getUsersByNewsId(@PathVariable long id) {
        return newsService.getUsersByNewsId(id);
    }
}
