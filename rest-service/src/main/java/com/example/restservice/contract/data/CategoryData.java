package com.example.restservice.contract.data;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.model.Category;
import com.example.restservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementarea contractului pentru gestionarea datelor categoriilor în baza de date.
 */
@Repository
public class CategoryData implements CategoryContract {
    /**
     * Utilizez repozitory-ul JPA pentru lucrul cu baza de date pentru tabela Category
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructorul care injectează dependența către CategoryRepository.
     *
     * @param categoryRepository Repository-ul pentru gestionarea entităților Category în baza de date
     */
    @Autowired
    public CategoryData(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Metoda care cauta toate categoriile
     *
     * @return lista de categorii
     */
    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    /**
     * Metoda care cauta o categorie dupa ID
     *
     * @param id ID-ul categoriei cautate
     * @return Categoria gasita sau null
     */
    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    /**
     * Metoda care salveaza categoriei in baza de date
     *
     * @param category Detaliile categoriei ce vreau sa o salvez
     * @return Categoria salvata
     */
    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    /**
     * Metoda care sterge o categorie
     *
     * @param id ID-ul categoriei ce urmeaza sa fie stearsa
     */
    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
