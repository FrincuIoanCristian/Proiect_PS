package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.SubscriptionContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.Subscription;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionContract subscriptionContract;
    private final CategoryContract categoryContract;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionContract subscriptionContract, CategoryContract categoryContract) {
        this.subscriptionContract = subscriptionContract;
        this.categoryContract = categoryContract;
    }


    /**
     * Returneaza lista tuturor abonamentelor.
     *
     * @return o lista cu toate abonamentele
     */
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionContract.findAll();
    }

    /**
     * Cauta un abonament dupa un id.
     *
     * @param id id-ul abonamentului cautat.
     * @return abonamentul cu id-ul gasit/null.
     */
    @Override
    public Subscription getSubscriptionById(long id) {
        return subscriptionContract.findById(id).orElse(null);
    }

    /**
     * Creaza si salveaza o noua inregistrare in tabel.
     *
     * @param subscription Abonamentul ce doresc sa il salvez in tabel.
     * @return Abonamentul nou creat.
     */
    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscription.setStartDate(LocalDate.now());
        Category category = categoryContract.findById(subscription.getCategory().getCategoryId()).orElse(null);
        assert category != null;
        subscription.setAmountPaid(category.getSubscriptionCost());

        return subscriptionContract.save(subscription);
    }

    /**
     * Face update la o inregistrare cu un id.
     *
     * @param id                 id-ul inregistrari ce doresc sa ii fac update.
     * @param updateSubscription Noile valori cu care doresc sa fac update.
     * @return Obiectul nou updatat.
     */
    @Override
    public Subscription updateSubscription(long id, Subscription updateSubscription) {
        Subscription subscription = subscriptionContract.findById(id).orElse(null);
        if (subscription != null) {
            updateSubscription.setSubscriptionId(subscription.getSubscriptionId());
            return subscriptionContract.save(updateSubscription);
        } else {
            return null;
        }
    }

    /**
     * Sterge o inregistrare dupa un id
     *
     * @param id id-ul elementului ce doresc sa-l sterg
     */
    @Override
    public void deleteSubscription(long id) {
        subscriptionContract.deleteById(id);
    }

    /**
     * Returnez lista de abonamente cu un userId.
     *
     * @param userId id-ul user-ului pentru care caut abonamentele
     * @return lista abonamentelor asociate unui user
     */
    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionContract.findByUserId(userId);
    }
}
