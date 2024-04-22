package com.example.restservice.controller;

import com.example.restservice.model.Category;
import com.example.restservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller-ul responsabil pentru gestionarea operatiilor legate de categorii.
 * Acesta expune API-uri REST pentru a obtine, crea, actualiza si sterge categorii.
 * /categories (GET)
 * /categories/{id} (GET)
 * /categories (POST)
 * /categories/{id} (PUT)
 * /categories/{id} (DELETE)
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Obtine toate categoriile
     *
     * @return lista de categorii
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Obtine o categorie dupa ID
     *
     * @param id ID-ul categoriei cautate
     * @return ResponseEntity conținand categoria gasita sau HttpStatus.NOT_FOUND daca categoria nu exista
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza o categorie noua.
     *
     * @param category Detaliile categoriei care urmeaza să fie create
     * @return ResponseEntity conținand categoria creat si HttpStatus.CREATED
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza o categorie existenta.
     *
     * @param id         ID-ul categoriei care urmeaza sa fie actualizata
     * @param newDetails Detaliile actualizate ale categoriei
     * @return ResponseEntity conținand categoria gasita sau HttpStatus.NOT_FOUND daca categoria nu exista
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category newDetails) {
        Category updateCategory = categoryService.updateCategory(id, newDetails);
        if (updateCategory != null) {
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge o categorie dupa ID.
     *
     * @param id ID-ul categorie care urmeaza sa fie sters
     * @return ResponseEntity cu HttpStatus.OK pentru confirmarea stergerii
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
