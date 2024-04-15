package com.example.restservice.service;

import com.example.restservice.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();
    Subscription getSubscriptionById(long id);
    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscription(long id, Subscription updateSubscription);
    void deleteSubscription(long id);
    List<Subscription> getSubscriptionsByUserId(Long userId);
}
