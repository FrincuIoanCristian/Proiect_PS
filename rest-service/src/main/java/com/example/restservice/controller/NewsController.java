package com.example.restservice.controller;

import com.example.restservice.model.News;
import com.example.restservice.model.Subscription;
import com.example.restservice.service.NewsService;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;
    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getUser(@PathVariable("id") long id) {
        News news = newsService.getNews(id);
        if(news != null){
            return new ResponseEntity<>(news, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<News> createUser(@RequestBody News news){
        News createNews = newsService.createNews(news);
        return new ResponseEntity<>(createNews,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<News> updateUser(@PathVariable("id") long id, @RequestBody News newDetails){
        News updateNews = newsService.updateNews(id,newDetails);
        if(updateNews != null){
            return new ResponseEntity<>(updateNews, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
