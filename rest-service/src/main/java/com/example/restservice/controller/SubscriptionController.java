package com.example.restservice.controller;

import com.example.restservice.model.Subscription;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    @Autowired
    private final SubscriptionService subscriptionService;
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }
    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable long id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if(subscription != null){
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription){
        Subscription createSubscription = subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>(createSubscription,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable long id, @RequestBody Subscription newDetails){
        Subscription updateSubscription = subscriptionService.updateSubscription(id,newDetails);
        if(updateSubscription != null){
            return new ResponseEntity<>(updateSubscription, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteSubscription(@PathVariable long id){
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSubscriptionsByUserId/{id}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long id) {
        return subscriptionService.getSubscriptionsByUserId(id);
    }
}
