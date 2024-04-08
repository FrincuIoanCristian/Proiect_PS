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

    /**
     * Returneaza lista tuturor abonamentelor.
     * @return o lista cu toate abonamentele
     */
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    /**
     * Cauta un abonament dupa un id.
     * @param id id-ul abonamentului cautat.
     * @return  abonamentul cu id-ul gasit/null.
     */
    public Subscription getSubscriptionById(long id){
        return subscriptionRepository.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza o noua inregistrare in tabel.
     * @param subscription Abonamentul ce doresc sa il salvez in tabel.
     * @return  Abonamentul nou creat.
     */
    public Subscription createSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    /**
     * Face update la o inregistrare cu un id.
     * @param id id-ul inregistrari ce doresc sa ii fac update.
     * @param updateSubscription    Noile valori cu care doresc sa fac update.
     * @return Obiectul nou updatat.
     */
    public Subscription updateSubscription(long id, Subscription updateSubscription){
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if(subscription != null){
            updateSubscription.setSubscriptionId(subscription.getSubscriptionId());
            return subscriptionRepository.save(updateSubscription);
        }else{
            return null;
        }
    }

    /**
     * Sterge o inregistrare dupa un id
     * @param id id-ul elementului ce doresc sa-l sterg
     */
    public void deleteSubscription(long id){
        subscriptionRepository.deleteById(id);
    }

    /**
     * Returnez lista de abonamente cu un userId.
     * @param userId    id-ul user-ului pentru care caut abonamentele
     * @return lista abonamentelor asociate unui user
     */
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
}
