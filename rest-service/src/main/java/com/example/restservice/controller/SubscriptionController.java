package com.example.restservice.controller;

import com.example.restservice.model.Subscription;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller-ul responsabil pentru gestionarea operatiilor legate de abonamente.
 * Acesta expune API-uri REST pentru a obtine, crea, actualiza si sterge abonamente.
 * /subscriptions (GET)
 * /subscriptions/{id} (GET)
 * /subscriptions (POST)
 * /subscriptions/{id} (PUT)
 * /subscriptions/{id} (DELETE)
 * /subscriptions/getSubscriptionsByUserId/{id} (GET)
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
     * @return lista de abonamente
     */
    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    /**
     * Obtine un abonament dupa un ID.
     *
     * @param id ID-ul abonamentului cautat
     * @return ResponseEntity conținand abonamentul gasit sau HttpStatus.NOT_FOUND daca abonamentul nu exista
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
     * Creaza un abonament nou
     *
     * @param subscription Detaliile abonamentului care urmeaza să fie creat
     * @return ResponseEntity conținand abonamentul creat si HttpStatus. CREATED
     */
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription createSubscription = subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>(createSubscription, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza un abonament existent
     *
     * @param id         ID-ul abonamentului care urmeaza sa fie actualizat
     * @param newDetails Detaliile actualizate ale abonamentului
     * @return ResponseEntity conținand abonamentul gasit sau HttpStatus.NOT_FOUND daca abonamentul nu exista
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
     * Sterge un abonament dupa ID.
     *
     * @param id ID-ul abonamentului care urmeaza sa fie sters
     * @return ResponseEntity cu HttpStatus.OK pentru confirmarea stergerii
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteSubscription(@PathVariable long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obtine o lista de abonamente a unui utilizator dupa ID.
     *
     * @param id ID-ul utilizatorului pentru care se cauta abonamentele
     * @return lista de abonamente asociate utilizatorului cu ID-ul precizat
     */
    @GetMapping("/getSubscriptionsByUserId/{id}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long id) {
        return subscriptionService.getSubscriptionsByUserId(id);
    }
}
