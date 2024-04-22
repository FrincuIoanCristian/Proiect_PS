package com.example.restservice.contract;

import com.example.restservice.contract.data.SubscriptionData;
import com.example.restservice.model.Category;
import com.example.restservice.model.Subscription;
import com.example.restservice.model.User;
import com.example.restservice.repository.SubscriptionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Teste pentru clasa SubscriptionContract.
 */
public class SubscriptionContractTest {
    @Mock
    private SubscriptionRepository subscriptionRepositoryMock;
    private SubscriptionContract subscriptionContract;

    /**
     * Initializarea testelor.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subscriptionContract = new SubscriptionData(subscriptionRepositoryMock);
    }

    /**
     * Test pentru metoda findAll.
     */
    @Test
    public void findAllTest() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1L, null, null, LocalDate.now(), 100));
        subscriptions.add(new Subscription(2L, null, null, LocalDate.now(), 200));
        when(subscriptionRepositoryMock.findAll()).thenReturn(subscriptions);
        List<Subscription> result = subscriptionContract.findAll();
        verify(subscriptionRepositoryMock).findAll();
        assertEquals(subscriptions, result);
    }

    /**
     * Test pentru metoda findById, cand il gaseste
     */
    @Test
    public void findByIdFoundTest() {
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription(subscriptionId, null, null, LocalDate.now(), 100);
        when(subscriptionRepositoryMock.findById(subscriptionId)).thenReturn(Optional.of(subscription));
        Optional<Subscription> result = subscriptionContract.findById(subscriptionId);
        verify(subscriptionRepositoryMock).findById(subscriptionId);
        assertEquals(Optional.of(subscription), result);
    }

    /**
     * Test pentru metoda findById, cand nu il gaseste.
     */
    @Test
    public void findByIdNotFoundTest() {
        Long subscriptionId = 1L;
        when(subscriptionRepositoryMock.findById(subscriptionId)).thenReturn(Optional.empty());
        Optional<Subscription> result = subscriptionContract.findById(subscriptionId);
        verify(subscriptionRepositoryMock).findById(subscriptionId);
        assertEquals(Optional.empty(), result);
    }

    /**
     * Test pentru metoda save.
     */
    @Test
    public void saveTest() {
        Category category = new Category(1, "CategoryName", 50.0);
        Subscription subscription = new Subscription(1, null, category, null, 100.0);
        when(subscriptionRepositoryMock.save(subscription)).thenReturn(subscription);
        Subscription result = subscriptionContract.save(subscription);
        verify(subscriptionRepositoryMock).save(subscription);
        assertEquals(subscription, result);
    }

    /**
     * Test pentru metoda deleteById.
     */
    @Test
    public void deleteByIdTest() {
        Long subscriptionId = 1L;
        subscriptionContract.deleteById(subscriptionId);
        verify(subscriptionRepositoryMock).deleteById(subscriptionId);
    }

    /**
     * Test pentru metoda findByUserId.
     */
    @Test
    public void findByUserIdTest() {
        Long userId = 1L;
        User user1 = new User(userId, "username1", "password1", "email1", "fullName1", "avatar1", 100.0);
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1L, user1, null, LocalDate.now(), 100.0));
        subscriptions.add(new Subscription(2L, user1, null, LocalDate.now(), 200.0));
        when(subscriptionRepositoryMock.findByUserId(userId)).thenReturn(subscriptions);
        List<Subscription> result = subscriptionContract.findByUserId(userId);
        verify(subscriptionRepositoryMock).findByUserId(userId);
        assertEquals(subscriptions, result);
    }
}
