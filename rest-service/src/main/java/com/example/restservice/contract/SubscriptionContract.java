package com.example.restservice.contract;

import com.example.restservice.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionContract {
    List<Subscription> findAll();

    Optional<Subscription> findById(Long id);

    Subscription save(Subscription subscription);

    void deleteById(Long id);

    List<Subscription> findByUserId(Long userId);
}
