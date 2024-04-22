package com.example.restservice.observer;

import com.example.restservice.model.Category;
import com.example.restservice.model.News;

/**
 * Aceasta reprezinta interfata pentru Observer-ul nostru
 */
public interface UserNewsObserver {
    /**
     * Metoda de notificare a observatorilor
     * @param news Stirea nou adaugata
     * @param category Categoria din care face parte stirea
     */
    void update(News news, Category category);
}