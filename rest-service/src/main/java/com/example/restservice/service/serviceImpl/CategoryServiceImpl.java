package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.model.Category;
import com.example.restservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementarea serviciului pentru gestionarea categoriilor în sistem.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryContract categoryContract;

    /**
     * Constructorul care injectează dependența către CategoryContract
     *
     * @param categoryContract Contractul pentru gestionarea datelor de tipul Category
     */
    @Autowired
    public CategoryServiceImpl(CategoryContract categoryContract) {
        this.categoryContract = categoryContract;
    }

    /**
     * Metoda care obține o listă cu toate categoriile din sistem.
     *
     * @return Lista cu categorii
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryContract.findAll();
    }

    /**
     * Metoda care obține o categorie după ID-ul specificat.
     *
     * @param id ID-ul categoriei căutate
     * @return Categoria cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryContract.findById(id).orElse(null);
    }

    /**
     * Metoda care creează o categorie nouă în sistem.
     *
     * @param category Categoria care urmează să fie creată
     * @return Categoria creată
     */
    @Override
    public Category createCategory(Category category) {
        return categoryContract.save(category);
    }

    /**
     * Metoda care actualizează o categorie existentă în sistem.
     *
     * @param id             ID-ul categoriei care urmează să fie actualizată
     * @param updateCategory Categoria actualizată
     * @return Categoria actualizată sau null dacă nu există o categorie cu ID-ul specificat
     */
    @Override
    public Category updateCategory(Long id, Category updateCategory) {
        Category category = categoryContract.findById(id).orElse(null);
        if (category != null) {
            updateCategory.setCategoryId(category.getCategoryId());
            return categoryContract.save(updateCategory);
        } else {
            return null;
        }
    }

    /**
     * Metoda care sterge o categorie existentă din sistem.
     *
     * @param id ID-ul categoriei care urmează să fie ștearsă
     */
    @Override
    public void deleteCategory(Long id) {
        categoryContract.deleteById(id);
    }
}
