package com.example.restservice.service;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.model.Category;
import com.example.restservice.service.serviceImpl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CategoryServiceTest {
    @Mock
    private CategoryContract categoryContractMock;
    private CategoryService categoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryContractMock);
    }

    @Test
    public void getAllCategoryTest() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "categoryName1", 20.0));
        categoryList.add(new Category(2, "categoryName2", 20.0));
        categoryList.add(new Category(3, "categoryName3", 20.0));
        Mockito.when(categoryContractMock.findAll()).thenReturn(categoryList);
        List<Category> allCategories = categoryService.getAllCategories();
        Mockito.verify(categoryContractMock).findAll();
        assertEquals(categoryList, allCategories);
    }

    @Test
    public void getCategoryByIdTest() {
        Long categoryId = 1L;
        Category category = new Category(1, "categoryName", 20.0);
        Mockito.when(categoryContractMock.findById(categoryId)).thenReturn(Optional.of(category));
        Category result = categoryService.getCategoryById(categoryId);
        Mockito.verify(categoryContractMock).findById(categoryId);
        assertEquals(category, result);
    }

    @Test
    public void getCategoryByIdNotFoundTest() {
        Long categoryId = 1L;
        Mockito.when(categoryContractMock.findById(categoryId)).thenReturn(Optional.empty());
        Category result = categoryService.getCategoryById(categoryId);
        Mockito.verify(categoryContractMock).findById(categoryId);
        assertNull(result);
    }

    @Test
    public void addCategoryTest() {
        Category category = new Category(1, "categoryName", 20.0);
        Mockito.when(categoryContractMock.save(category)).thenReturn(category);
        Category result = categoryService.createCategory(category);
        Mockito.verify(categoryContractMock).save(category);
        assertEquals(category, result);
    }

    @Test
    public void updateCategoryTest() {
        Long categoryId = 1L;
        Category existingCategory = new Category(1, "categoryName", 20.0);
        Category updatedCategory = new Category(1, "categoryName2", 20.0);
        Mockito.when(categoryContractMock.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        Mockito.when(categoryContractMock.save(updatedCategory)).thenReturn(existingCategory);
        Category result = categoryService.updateCategory(categoryId, updatedCategory);
        Mockito.verify(categoryContractMock).findById(categoryId);
        Mockito.verify(categoryContractMock).save(updatedCategory);
        assertEquals(existingCategory, result);
    }

    @Test
    public void updateCategoryNotFoundTest() {
        Long categoryId = 1L;
        Category category = new Category(categoryId, "categoryName1", 20.0);
        Mockito.when(categoryContractMock.findById(categoryId)).thenReturn(Optional.empty());
        Category result = categoryService.updateCategory(categoryId, category);
        Mockito.verify(categoryContractMock).findById(categoryId);
        assertNull(result);
    }

    @Test
    public void deleteCategoryTest() {
        Long categoryId = 1L;
        categoryService.deleteCategory(categoryId);
        Mockito.verify(categoryContractMock).deleteById(categoryId);
    }

}
