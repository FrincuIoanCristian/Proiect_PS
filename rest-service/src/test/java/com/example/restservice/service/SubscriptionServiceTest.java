package com.example.restservice.service;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.SubscriptionContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.Subscription;
import com.example.restservice.model.User;
import com.example.restservice.service.serviceImpl.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SubscriptionServiceTest {
    @Mock
    private SubscriptionContract subscriptionContract;
    @Mock
    private CategoryContract categoryContract;

    private SubscriptionService subscriptionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subscriptionService = new SubscriptionServiceImpl(subscriptionContract, categoryContract);
    }

    @Test
    public void getAllSubscriptionTest() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1L, null, null, LocalDate.now(), 100));
        subscriptions.add(new Subscription(2L, null, null, LocalDate.now(), 200));
        subscriptions.add(new Subscription(3L, null, null, LocalDate.now(), 300));
        Mockito.when(subscriptionContract.findAll()).thenReturn(subscriptions);
        List<Subscription> allSubscriptions = subscriptionService.getAllSubscriptions();
        Mockito.verify(subscriptionContract).findAll();
        assertEquals(subscriptions, allSubscriptions);
    }

    @Test
    public void getSubscriptionByIdTest() {
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription(subscriptionId, null, null, LocalDate.now(), 100);
        Mockito.when(subscriptionContract.findById(subscriptionId)).thenReturn(Optional.of(subscription));
        Subscription foundSubscription = subscriptionService.getSubscriptionById(subscriptionId);
        Mockito.verify(subscriptionContract).findById(subscriptionId);
        assertEquals(subscription, foundSubscription);
    }

    @Test
    public void getSubscriptionByIdNotFoundTest() {
        Long subscriptionId = 1L;
        Mockito.when(subscriptionContract.findById(subscriptionId)).thenReturn(Optional.empty());
        Subscription foundSubscription = subscriptionService.getSubscriptionById(subscriptionId);
        Mockito.verify(subscriptionContract).findById(subscriptionId);
        assertNull(foundSubscription);
    }


    @Test
    public void addSubscriptionTest() {
        Category category = new Category(1, "CategoryName", 50.0);
        Subscription subscription = new Subscription(1, null, category, null, 100.0);
        Mockito.when(categoryContract.findById(subscription.getCategory().getCategoryId())).thenReturn(Optional.of(category));
        Mockito.when(subscriptionContract.save(subscription)).thenReturn(subscription);

        Subscription result = subscriptionService.createSubscription(subscription);
        Mockito.verify(categoryContract).findById(subscription.getCategory().getCategoryId());
        Mockito.verify(subscriptionContract).save(subscription);
        assertEquals(subscription, result);
    }

    @Test
    public void updateSubscriptionTest() {
        Long subscriptionId = 1L;
        Subscription existingSubscription = new Subscription(subscriptionId, null, null, LocalDate.now(), 100.0);
        Subscription updatedSubscription = new Subscription(subscriptionId, null, null, LocalDate.now(), 200.0);

        Mockito.when(subscriptionContract.findById(subscriptionId)).thenReturn(Optional.of(existingSubscription));
        Mockito.when(subscriptionContract.save(updatedSubscription)).thenReturn(existingSubscription);
        Subscription result = subscriptionService.updateSubscription(subscriptionId, updatedSubscription);
        Mockito.verify(subscriptionContract).findById(subscriptionId);
        Mockito.verify(subscriptionContract).save(updatedSubscription);
        assertEquals(existingSubscription, result);
    }

    @Test
    public void updateSubscriptionNotFoundTest() {
        Long subscriptionId = 1L;
        Subscription updateSubscription = new Subscription(subscriptionId, null, null, LocalDate.now(), 100.0);
        Mockito.when(subscriptionContract.findById(subscriptionId)).thenReturn(Optional.empty());
        Subscription result = subscriptionService.updateSubscription(subscriptionId, updateSubscription);
        Mockito.verify(subscriptionContract).findById(subscriptionId);
        assertNull(result);
    }

    @Test
    public void deleteSubscriptionTest() {
        Long subscriptionId = 1L;
        subscriptionService.deleteSubscription(subscriptionId);
        Mockito.verify(subscriptionContract).deleteById(subscriptionId);
    }

    @Test
    public void testGetSubscriptionsByUserId() {
        Long userId = 1L;
        User user1 = new User(userId, "username1", "password1", "email1", "fullName1", "avatar1", 100.0);
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1L, user1, null, LocalDate.now(), 100.0));
        subscriptions.add(new Subscription(2L, user1, null, LocalDate.now(), 200.0));
        Mockito.when(subscriptionContract.findByUserId(userId)).thenReturn(subscriptions);
        List<Subscription> allSubscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        Mockito.verify(subscriptionContract).findByUserId(userId);
        assertEquals(subscriptions, allSubscriptions);
    }

}
