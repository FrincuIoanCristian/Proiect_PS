package com.example.restservice.controller;

import com.example.restservice.model.Category;
import com.example.restservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    /**
     * Obtine toate categoriile
     * @return o lista cu toate categoriile
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Obtine o categorie dupa un id
     * @param id id-ul categoriei cautate
     * @return Un obiect ResponseEntity care contine categoria si statusul HTTP corespunzator.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        if(category != null){
            return new ResponseEntity<>(category, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza o categorie noua.
     * @param category detaliile noii categorii.
     * @return Un obiect ResponseEntity care contine categoria si statusul HTTP corespunzator.
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
    }

    /**
     * Actualizeaza o categorie existenta, cautata dupa id
     * @param id id-ul categoriei cautate
     * @param newDetails noile detalii
     * @return Un obiect ResponseEntity care contine categoria si statusul HTTP corespunzator.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category newDetails){
        Category updateCategory = categoryService.updateCategory(id,newDetails);
        if(updateCategory != null){
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge o categorie dupa un id
     * @param id id-ul categorie ce doresc sa o sterg
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
