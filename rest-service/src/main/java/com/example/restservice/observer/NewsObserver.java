package com.example.restservice.observer;

import com.example.restservice.model.News;

/**
 * Aceasta reprezinta interfata pentru Observer-ul nostru
 */
public interface NewsObserver {
    void update(News news);
}