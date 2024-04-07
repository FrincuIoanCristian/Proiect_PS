package com.example.restservice.controller;

import com.example.restservice.model.Category;
import com.example.restservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getUser(@PathVariable("id") long id) {
        Category category = categoryService.getCategory(id);
        if(category != null){
            return new ResponseEntity<>(category, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Category> createUser(@RequestBody Category category){
        Category createCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateUser(@PathVariable("id") long id, @RequestBody Category newDetails){
        Category updateCategory = categoryService.updateCategory(id,newDetails);
        if(updateCategory != null){
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
