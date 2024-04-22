package com.example.restservice.contract;

import com.example.restservice.contract.data.CategoryData;
import com.example.restservice.model.Category;
import com.example.restservice.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Teste pentru clasa CategoryContract.
 */
public class CategoryContractTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    private CategoryContract categoryContract;

    /**
     * Initializarea testelor.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryContract = new CategoryData(categoryRepositoryMock);
    }

    /**
     * Test pentru metoda findAll.
     */
    @Test
    public void findAllTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "categoryName1", 20.0));
        categories.add(new Category(2, "categoryName2", 20.0));
        when(categoryRepositoryMock.findAll()).thenReturn(categories);
        List<Category> result = categoryContract.findAll();
        verify(categoryRepositoryMock).findAll();
        assertEquals(categories, result);
    }

    /**
     * Test pentru metoda findById, cand il gaseste
     */
    @Test
    public void findByIdFoundTest() {
        Long categoryId = 1L;
        Category category = new Category(1, "categoryName", 20.0);
        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryContract.findById(categoryId);
        verify(categoryRepositoryMock).findById(categoryId);
        assertEquals(Optional.of(category), result);
    }

    /**
     * Test pentru metoda findById, cand nu il gaseste.
     */
    @Test
    public void findByIdNotFoundTest() {
        Long categoryId = 1L;
        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.empty());
        Optional<Category> result = categoryContract.findById(categoryId);
        verify(categoryRepositoryMock).findById(categoryId);
        assertEquals(Optional.empty(), result);
    }

    /**
     * Test pentru metoda save.
     */
    @Test
    public void saveTest() {
        Category category = new Category(1, "categoryName", 20.0);
        when(categoryRepositoryMock.save(category)).thenReturn(category);
        Category result = categoryContract.save(category);
        verify(categoryRepositoryMock).save(category);
        assertEquals(category, result);
    }

    /**
     * Test pentru metoda deleteById.
     */
    @Test
    public void deleteByIdTest() {
        Long categoryId = 1L;
        categoryContract.deleteById(categoryId);
        verify(categoryRepositoryMock).deleteById(categoryId);
    }
}
