package com.example.restservice.service.serviceImpl;

import com.example.restservice.model.Category;
import com.example.restservice.repository.CategoryRepository;
import com.example.restservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Returneaza toate categoriile
     * @return o lista cu toate categoriile
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Returneaza o categorie dupa id
     * @param id id-ul categoriei cautata
     * @return Un obiect de tipul categorie cu id-ul respectiv
     */
    @Override
    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza in baza de date o inregistrare
     * @param category Datele ce doresc sa le salvez in baza de date
     * @return Categoria creata
     */
    @Override
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    /**
     * Face update pe o inregistrare cu un anumit id
     * @param id id-ul pe care se doreste sa se faca update
     * @param updateCategory noile date ale inregistrari
     * @return obiectul nou actualizat
     */
    @Override
    public Category updateCategory(long id, Category updateCategory){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            updateCategory.setCategoryId(category.getCategoryId());
            return categoryRepository.save(updateCategory);
        }else{
            return null;
        }
    }

    /**
     * sterge din baza de date o inregistrare dupa un id
     * @param id id-ul inregistrari ce se doreste sa se stearga
     */
    @Override
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}
