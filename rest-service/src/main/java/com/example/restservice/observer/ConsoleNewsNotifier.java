package com.example.restservice.observer;

import com.example.restservice.model.News;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNewsNotifier implements NewsObserver {
    @Override
    public void update(News news) {
        System.out.println("Stire noua: " + news.getTitle());
    }
}