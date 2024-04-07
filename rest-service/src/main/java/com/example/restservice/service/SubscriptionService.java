package com.example.restservice.service;

import com.example.restservice.model.Subscription;
import com.example.restservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private final SubscriptionRepository subscriptionRepository;
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    public Subscription getSubscription(long id){
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription createSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(long id, Subscription updateSubscription){
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if(subscription != null){
            updateSubscription.setSubscriptionId(subscription.getSubscriptionId());
            return subscriptionRepository.save(updateSubscription);
        }else{
            return null;
        }
    }
    public void deleteSubscription(long id){
        subscriptionRepository.deleteById(id);
    }
}
