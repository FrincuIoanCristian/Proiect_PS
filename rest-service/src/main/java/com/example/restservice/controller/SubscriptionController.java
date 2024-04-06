package com.example.restservice.controller;

import com.example.restservice.model.Subscription;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private SubscriptionService subscriptionService;
    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Subscription> getUser(@PathVariable("id") long id) {
        Subscription subscription = subscriptionService.getSubscription(id);
        if(subscription != null){
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Subscription> createUser(@RequestBody Subscription subscription){
        Subscription createSubscription = subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>(createSubscription,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subscription> updateUser(@PathVariable("id") long id, @RequestBody Subscription newDetails){
        Subscription updateSubscription = subscriptionService.updateSubscription(id,newDetails);
        if(updateSubscription != null){
            return new ResponseEntity<>(updateSubscription, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
