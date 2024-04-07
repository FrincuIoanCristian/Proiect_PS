package com.example.restservice.controller;

import com.example.restservice.model.News;
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
    @Autowired
    private final NewsService newsService;
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNews(@PathVariable long id) {
        News news = newsService.getNewsById(id);
        if(news != null){
            return new ResponseEntity<>(news, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news){
        News createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable long id, @RequestBody News newDetails){
        News updateNews = newsService.updateNews(id,newDetails);
        if(updateNews != null){
            return new ResponseEntity<>(updateNews, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteNews(@PathVariable long id){
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/news")
    public List<News> getNewsByCategoryName(@RequestParam String categoryName) {
        return newsService.getNewsByCategoryName(categoryName);
    }
}
