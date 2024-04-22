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

/**
 * Controller-ul responsabil pentru gestionarea operatiilor legate de stiri.
 * Acesta expune API-uri REST pentru a obtine, crea, actualiza si sterge stiri.
 * /news (GET)
 * /news/{id} (GET)
 * /news (POST)
 * /news/{id} (PUT)
 * /news/{id} (DELETE)
 * /news/getNewsByCategoryName (GET)
 * /news/getUsersByNewsId/{id} (GET)
 */
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
     * @return lisat de stiri
     */
    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    /**
     * Obtin o stire dupa ID
     *
     * @param id Id-ul stirii cautate.
     * @return ResponseEntity conținand stirea gasita sau HttpStatus.NOT_FOUND daca stirea nu exista.
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
     * @param news Detaliile stirii care urmeaza să fie create
     * @return ResponseEntity conținand stirea creata si HttpStatus.CREATED
     */
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza o stire existenta.
     *
     * @param id         ID-ul stirii care urmeaza sa fie actualizata
     * @param newDetails Detaliile actualizate ale stirii
     * @return ResponseEntity conținand stirea gasita sau HttpStatus.NOT_FOUND daca stirea nu exista.
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
     * Sterge o stire dupa ID.
     *
     * @param id ID-ul stirii care urmeaza sa fie stearsa
     * @return ResponseEntity cu HttpStatus.OK pentru confirmarea stergerii
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteNews(@PathVariable long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obtin toate stirile unei categorii dupa numele Categoriei
     *
     * @param categoryName numele categoriei pentru care se cauta stiri
     * @return o lista cu stiri
     */
    @GetMapping("/getNewsByCategoryName")
    public List<News> getNewsByCategoryName(@RequestParam String categoryName) {
        return newsService.getNewsByCategoryName(categoryName);
    }

    /**
     * Obtin toti utilizatori care sunt abonati la categoria din care face parte stirea
     * @param id    ID-ul stirii pentru care se cauta utilizatori
     * @return lisat de utilizatori
     */
    @GetMapping("/getUsersByNewsId/{id}")
    public List<User> getUsersByNewsId(@PathVariable long id) {
        return newsService.getUsersByNewsId(id);
    }
}
