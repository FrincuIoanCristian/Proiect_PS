package com.example.restservice.contract;

import com.example.restservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryContract {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}
