package com.example.restservice.service;

import com.example.restservice.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, Category updateCategory);

    void deleteCategory(Long id);
}
