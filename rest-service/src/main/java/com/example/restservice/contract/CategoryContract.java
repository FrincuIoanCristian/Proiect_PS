package com.example.restservice.contract;

import com.example.restservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryContract {
    List<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category category);

    void deleteById(long id);
}
