package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.CategoryContract;
import com.example.restservice.contract.SubscriptionContract;
import com.example.restservice.contract.UserContract;
import com.example.restservice.model.Category;
import com.example.restservice.model.Subscription;
import com.example.restservice.model.User;
import com.example.restservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementarea contractului pentru gestionarea datelor abonamentelor în baza de date.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionContract subscriptionContract;
    private final CategoryContract categoryContract;
    private final UserContract userContract;

    /**
     * Constructorul care injectează dependența către SubscriptionContract si CategoryContract
     * @param subscriptionContract Contractul pentru gestionarea datelor de tipul Subscription
     * @param categoryContract Contractul pentru gestionarea datelor de tipul Category
     */
    @Autowired
    public SubscriptionServiceImpl(SubscriptionContract subscriptionContract, CategoryContract categoryContract, UserContract userContract) {
        this.subscriptionContract = subscriptionContract;
        this.categoryContract = categoryContract;
        this.userContract = userContract;
    }

    /**
     * Metoda care obține o listă cu toate abonamentele din sistem.
     *
     * @return Lista cu abonamente
     */
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionContract.findAll();
    }

    /**
     * Metoda care obține un abonament după ID-ul specificat.
     *
     * @param id ID-ul abonamentului căutat
     * @return Abonamentul cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionContract.findById(id).orElse(null);
    }

    /**
     * Metoda care creează un abonament nou în sistem.
     *
     * @param subscription Abonamentul care urmează să fie creat
     * @return Abonamentul creat
     */
    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscription.setStartDate(LocalDate.now());
        Category category = categoryContract.findById(subscription.getCategory().getCategoryId()).orElse(null);
        User user = userContract.findById(subscription.getUser().getUserId()).orElse(null);
        assert category != null;
        subscription.setAmountPaid(category.getSubscriptionCost());
        assert user != null;
        user.setBalance(user.getBalance()-subscription.getAmountPaid());
        User save = userContract.save(user);
        subscription.setUser(save);

        return subscriptionContract.save(subscription);
    }

    /**
     * Metoda care actualizează un abonament existent în sistem.
     *
     * @param id                 ID-ul abonamentului care urmează să fie actualizat
     * @param updateSubscription Abonamentul actualizat
     * @return Abonamentul actualizat sau null dacă nu există un abonament cu ID-ul specificat
     */
    @Override
    public Subscription updateSubscription(Long id, Subscription updateSubscription) {
        Subscription subscription = subscriptionContract.findById(id).orElse(null);
        if (subscription != null) {
            updateSubscription.setSubscriptionId(subscription.getSubscriptionId());
            return subscriptionContract.save(updateSubscription);
        } else {
            return null;
        }
    }

    /**
     * Metoda care sterge un abonament existent din sistem.
     *
     * @param id ID-ul abonamentului care urmează să fie șters
     */
    @Override
    public void deleteSubscription(Long id) {
        subscriptionContract.deleteById(id);
    }

    /**
     * Metoda care obține o listă cu abonamentele asociate unui anumit utilizator.
     *
     * @param userId ID-ul utilizatorului pentru care se caută abonamentele
     * @return Lista cu abonamentele asociate utilizatorului specificat
     */
    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionContract.findByUserId(userId);
    }
}
