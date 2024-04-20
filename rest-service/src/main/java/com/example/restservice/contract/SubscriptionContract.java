package com.example.restservice.contract;

import com.example.restservice.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionContract {
    List<Subscription> findAll();

    Optional<Subscription> findById(long id);

    Subscription save(Subscription subscription);

    void deleteById(long id);

    List<Subscription> findByUserId(Long userId);
}
