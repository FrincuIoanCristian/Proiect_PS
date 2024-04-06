package com.example.restservice.service;

import com.example.restservice.model.Category;
import com.example.restservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(long id, Category newDetails){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            category.setCategoryName(newDetails.getCategoryName());
            category.setSubscriptionCost(newDetails.getSubscriptionCost());
            return categoryRepository.save(category);
        }else{
            return null;
        }
    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}
