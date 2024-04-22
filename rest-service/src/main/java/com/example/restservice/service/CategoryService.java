package com.example.restservice.service;

import com.example.restservice.model.Category;

import java.util.List;

/**
 * Interfața care definește operațiile disponibile pentru gestionarea categoriilor în sistem.
 * Aceste operații includ obținerea tuturor categoriilor, obținerea unei categorii după ID,
 * crearea, actualizarea și ștergerea categoriilor.
 */
public interface CategoryService {
    /**
     * Obține o listă cu toate categoriile din sistem.
     *
     * @return Lista cu categorii
     */
    List<Category> getAllCategories();

    /**
     * Obține o categorie după ID-ul specificat.
     *
     * @param id ID-ul categoriei căutate
     * @return Categoria cu ID-ul specificat sau null dacă nu există
     */
    Category getCategoryById(Long id);

    /**
     * Creează o categorie nouă în sistem.
     *
     * @param category Categoria care urmează să fie creată
     * @return Categoria creată
     */
    Category createCategory(Category category);

    /**
     * Actualizează o categorie existentă în sistem.
     *
     * @param id             ID-ul categoriei care urmează să fie actualizată
     * @param updateCategory Categoria actualizată
     * @return Categoria actualizată sau null dacă nu există o categorie cu ID-ul specificat
     */
    Category updateCategory(Long id, Category updateCategory);

    /**
     * Șterge o categorie existentă din sistem.
     *
     * @param id ID-ul categoriei care urmează să fie ștearsă
     */
    void deleteCategory(Long id);
}