package com.example.restservice.service;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.NewsContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import com.example.restservice.observer.EmailService;
import com.example.restservice.service.serviceImpl.NewsServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;

public class NewsServiceTest {
    @Mock
    private NewsContract newsContractMock;
    @Mock
    private CategoryContract categoryContractMock;
    @Mock
    private EmailService emailServiceMock;
    private NewsService newsService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsService = new NewsServiceImpl(newsContractMock, categoryContractMock, emailServiceMock);
    }

    @Test
    public void testGetAllNews() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1L, null, "title1", "content1", null));
        newsList.add(new News(2L, null, "title2", "content2", null));
        Mockito.when(newsContractMock.findAll()).thenReturn(newsList);
        List<News> allNews = newsService.getAllNews();
        Mockito.verify(newsContractMock).findAll();
        assertEquals(newsList, allNews);
    }

    @Test
    public void testGetNewsById() {
        Long newsId = 1L;
        News news = new News(newsId, null, "title", "content", null);
        Mockito.when(newsContractMock.findById(newsId)).thenReturn(Optional.of(news));
        News result = newsService.getNewsById(newsId);
        Mockito.verify(newsContractMock).findById(newsId);
        assertEquals(news, result);
    }

    @Test
    public void testGetNewsByIdNotFound() {
        Long newsId = 1L;
        Mockito.when(newsContractMock.findById(newsId)).thenReturn(Optional.empty());
        News result = newsService.getNewsById(newsId);
        Mockito.verify(newsContractMock).findById(newsId);
        assertNull(result);
    }

    @Test
    public void testCreateNews() {
        Long newsId = 1L;
        Category category = new Category(newsId, "categoryName", 100.0);
        News news = new News(1, category, "title", "content", null);
        Mockito.when(categoryContractMock.findById(newsId)).thenReturn(Optional.of(category));
        Mockito.when(newsContractMock.save(news)).thenReturn(news);
        News result = newsService.createNews(news);
        Mockito.verify(newsContractMock).save(news);
        assertEquals(news, result);
    }

    @Test
    public void testUpdateNews() {
        Long newsId = 1L;
        Category category = new Category(1, "categoryName", 100.0);
        News existingNews = new News(newsId, category, "title", "content", null);
        News updatedNews = new News(newsId, category, "titleUpdate", "contentUpdate", null);
        Mockito.when(newsContractMock.findById(newsId)).thenReturn(Optional.of(existingNews));
        Mockito.when(newsContractMock.save(updatedNews)).thenReturn(existingNews);
        News result = newsService.updateNews(newsId, updatedNews);
        Mockito.verify(newsContractMock).findById(newsId);
        Mockito.verify(newsContractMock).save(updatedNews);
        assertEquals(existingNews, result);
    }

    @Test
    public void testUpdateNewsNotFound() {
        Long newsId = 1L;
        News updatedNews = new News(newsId, null, "title", "content", null);
        Mockito.when(newsContractMock.findById(newsId)).thenReturn(Optional.empty());
        News result = newsService.updateNews(newsId, updatedNews);
        Mockito.verify(newsContractMock).findById(newsId);
        assertNull(result);
    }

    @Test
    public void testDeleteNews() {
        Long newsId = 1L;
        newsService.deleteNews(newsId);
        Mockito.verify(newsContractMock).deleteById(newsId);
    }

    @Test
    public void testGetNewsByCategoryName() {
        String categoryName = "TestCategory";
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1L, null, categoryName, "content1", null));
        newsList.add(new News(2L, null, categoryName, "content2", null));
        Mockito.when(newsContractMock.findByCategoryName(categoryName)).thenReturn(newsList);
        List<News> result = newsService.getNewsByCategoryName(categoryName);
        Mockito.verify(newsContractMock).findByCategoryName(categoryName);
        assertEquals(newsList, result);
    }

    @Test
    public void testGetUsersByNewsId() {
        Long newsId = 1L;
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "username", "password", "email", "fullName", "avatar", 100.0));
        Mockito.when(newsContractMock.findUsersByNewsId(anyLong())).thenReturn(userList);
        List<User> result = newsService.getUsersByNewsId(newsId);
        Mockito.verify(newsContractMock).findUsersByNewsId(newsId);
        assertEquals(userList, result);
    }
}
