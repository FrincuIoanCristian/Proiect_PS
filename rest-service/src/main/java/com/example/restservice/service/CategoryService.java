package com.example.restservice.service;

import com.example.restservice.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(long id);

    Category createCategory(Category category);

    Category updateCategory(long id, Category updateCategory);

    void deleteCategory(long id);
}
