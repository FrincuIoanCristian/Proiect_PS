package com.example.restservice.contract.data;

import com.example.restservice.contract.SubscriptionContract;
import com.example.restservice.model.Subscription;
import com.example.restservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementarea contractului pentru gestionarea datelor abonamentelor în baza de date.
 */
@Repository
public class SubscriptionData implements SubscriptionContract {
    /**
     * Utilizez repozitory-ul JPA pentru lucrul cu baza de date pentru tabela Subscription
     */
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructorul care injectează dependența către SubscriptionRepository.
     *
     * @param subscriptionRepository Repository-ul pentru gestionarea entităților Subscription în baza de date
     */
    @Autowired
    public SubscriptionData(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Metoda care cauta toate abonamentele
     *
     * @return lista de abonamente
     */
    @Override
    public List<Subscription> findAll() {
        return this.subscriptionRepository.findAll();
    }

    /**
     * Metoda care cauta un abonament dupa ID
     *
     * @param id ID-ul abonamentului cautat
     * @return Abonamentul gasit sau null
     */
    @Override
    public Optional<Subscription> findById(Long id) {
        return this.subscriptionRepository.findById(id);
    }

    /**
     * Metoda care salveaza abonamentul in baza de date
     *
     * @param subscription Detaliile abonamentului ce vreau sa il salvez
     * @return Abonamentul salvat
     */
    @Override
    public Subscription save(Subscription subscription) {
        return this.subscriptionRepository.save(subscription);
    }

    /**
     * Metoda care sterge un abonament
     *
     * @param id ID-ul abonamentului ce urmeaza sa fie sters
     */
    @Override
    public void deleteById(Long id) {
        this.subscriptionRepository.deleteById(id);
    }

    /**
     * Metoda care cauta toate abonametele dupa un ID al utilizatorului
     *
     * @param userId ID-ul utilizatorului
     * @return Lista de abonamente
     */
    @Override
    public List<Subscription> findByUserId(Long userId) {
        return this.subscriptionRepository.findByUserId(userId);
    }
}
