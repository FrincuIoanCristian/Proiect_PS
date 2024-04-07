package com.example.restservice.service;

import com.example.restservice.model.Category;
import com.example.restservice.model.Subscription;
import com.example.restservice.repository.CategoryRepository;
import com.example.restservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription getSubscription(long id){
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription createSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(long id, Subscription newDetails){
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if(subscription != null){
            subscription.setUser(newDetails.getUser());
            subscription.setCategory(newDetails.getCategory());
            subscription.setStartDate(newDetails.getStartDate());
            subscription.setAmountPaid(newDetails.getAmountPaid());
            subscription.setActive(newDetails.isActive());
            return subscriptionRepository.save(subscription);
        }else{
            return null;
        }
    }
    public void deleteSubscription(long id){
        subscriptionRepository.deleteById(id);
    }
}
