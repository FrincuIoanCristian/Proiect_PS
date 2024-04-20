package com.example.restservice.contract.data;

import com.example.restservice.contract.SubscriptionContract;
import com.example.restservice.model.Subscription;
import com.example.restservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionData implements SubscriptionContract {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionData(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> findAll() {
        return this.subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> findById(long id) {
        return this.subscriptionRepository.findById(id);
    }

    @Override
    public Subscription save(Subscription subscription) {
        return this.subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteById(long id) {
        this.subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> findByUserId(Long userId) {
        return this.subscriptionRepository.findByUserId(userId);
    }
}
