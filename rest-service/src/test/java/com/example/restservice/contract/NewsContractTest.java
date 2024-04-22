package com.example.restservice.contract;

import com.example.restservice.contract.data.NewsData;
import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.repository.NewsRepository;
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
 * Teste pentru clasa NewsContract.
 */
public class NewsContractTest {
    @Mock
    private NewsRepository newsRepositoryMock;
    private NewsData newsData;

    /**
     * Initializarea testelor.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsData = new NewsData(newsRepositoryMock);
    }

    /**
     * Test pentru metoda findAll.
     */
    @Test
    public void findAllTest() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1L, null, "title1", "content1", null));
        newsList.add(new News(2L, null, "title2", "content2", null));
        when(newsRepositoryMock.findAll()).thenReturn(newsList);
        List<News> result = newsData.findAll();
        verify(newsRepositoryMock).findAll();
        assertEquals(newsList, result);
    }

    /**
     * Test pentru metoda findById, cand il gaseste
     */
    @Test
    public void findByIdFoundTest() {
        Long newsId = 1L;
        News news = new News(newsId, null, "title", "content", null);
        when(newsRepositoryMock.findById(newsId)).thenReturn(Optional.of(news));
        Optional<News> result = newsData.findById(newsId);
        verify(newsRepositoryMock).findById(newsId);
        assertEquals(Optional.of(news), result);
    }

    /**
     * Test pentru metoda findById, cand nu il gaseste.
     */
    @Test
    public void findByIdNotFoundTest() {
        Long newsId = 1L;
        when(newsRepositoryMock.findById(newsId)).thenReturn(Optional.empty());
        Optional<News> result = newsData.findById(newsId);
        verify(newsRepositoryMock).findById(newsId);
        assertEquals(Optional.empty(), result);
    }

    /**
     * Test pentru metoda save.
     */
    @Test
    public void saveTest() {
        Category category = new Category(1, "categoryName", 100.0);
        News news = new News(1, category, "title", "content", null);
        when(newsRepositoryMock.save(news)).thenReturn(news);
        News result = newsData.save(news);
        verify(newsRepositoryMock).save(news);
        assertEquals(news, result);
    }

    /**
     * Test pentru metoda deleteById.
     */
    @Test
    public void deleteByIdTest() {
        Long newsId = 1L;
        newsData.deleteById(newsId);
        verify(newsRepositoryMock).deleteById(newsId);
    }

    /**
     * Test pentru metoda findByCategoryName.
     */
    @Test
    public void findByCategoryNameTest() {
        String categoryName = "Category";
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1L, null, categoryName, "content1", null));
        newsList.add(new News(2L, null, categoryName, "content2", null));
        when(newsRepositoryMock.findByCategoryName(categoryName)).thenReturn(newsList);
        List<News> result = newsData.findByCategoryName(categoryName);
        verify(newsRepositoryMock).findByCategoryName(categoryName);
        assertEquals(newsList, result);
    }

    /**
     * Test pentru metoda findUsersByNewsId.
     */
    @Test
    public void findUsersByNewsIdTest() {
        Long newsId = 1L;
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "username", "password", "email", "fullName", "avatar", 100.0));
        userList.add(new User(2L, "username2", "password2", "email2", "fullName2", "avatar2", 200.0));
        when(newsRepositoryMock.findUsersByNewsId(newsId)).thenReturn(userList);
        List<User> result = newsData.findUsersByNewsId(newsId);
        verify(newsRepositoryMock).findUsersByNewsId(newsId);
        assertEquals(userList, result);
    }
}
