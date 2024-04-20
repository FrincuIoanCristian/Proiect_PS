package com.example.restservice.observer;

import com.example.restservice.model.Category;
import com.example.restservice.model.News;

/**
 * Aceasta reprezinta interfata pentru Observer-ul nostru
 */
public interface UserNewsObserver {
    void update(News news, Category category);
}