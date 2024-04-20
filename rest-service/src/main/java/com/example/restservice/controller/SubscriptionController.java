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
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Obtine toate abonamentele
     *
     * @return o lista cu toate abonamentele
     */
    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    /**
     * Obtine un abonament dupa un id
     *
     * @param id id-ul pentru care se cauta
     * @return Un obiect ResponseEntity care contine abonamentul si statusul HTTP corespunzator.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable long id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza un nou abonament
     *
     * @param subscription detaliile noului abonament
     * @return Un obiect ResponseEntity care contine abonamentul si statusul HTTP corespunzator.
     */
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription createSubscription = subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>(createSubscription, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza un nou abonament
     *
     * @param id         id-ul abonamentului ce vreau sa il actualizez
     * @param newDetails detaliile noului abonament
     * @return Un obiect ResponseEntity care contine abonamentul si statusul HTTP corespunzator.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable long id, @RequestBody Subscription newDetails) {
        Subscription updateSubscription = subscriptionService.updateSubscription(id, newDetails);
        if (updateSubscription != null) {
            return new ResponseEntity<>(updateSubscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge un abonament dupa un id
     *
     * @param id id-ul abonamentului ce doreste sa se stearga
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteSubscription(@PathVariable long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obtine o lista de abonamente a unui utilizator dupa un anumit id
     *
     * @param id id-ul utilizatorului pentru care se cauta abonamentele
     * @return lista de abonamente asociate utilizatorului cu id-ul precizat
     */
    @GetMapping("/getSubscriptionsByUserId/{id}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long id) {
        return subscriptionService.getSubscriptionsByUserId(id);
    }
}
