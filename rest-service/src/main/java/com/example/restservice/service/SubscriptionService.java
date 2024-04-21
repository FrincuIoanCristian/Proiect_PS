package com.example.restservice.service;

import com.example.restservice.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();

    Subscription getSubscriptionById(Long id);

    Subscription createSubscription(Subscription subscription);

    Subscription updateSubscription(Long id, Subscription updateSubscription);

    void deleteSubscription(Long id);

    List<Subscription> getSubscriptionsByUserId(Long userId);
}
