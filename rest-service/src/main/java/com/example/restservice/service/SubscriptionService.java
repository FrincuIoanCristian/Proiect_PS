package com.example.restservice.service;

import com.example.restservice.model.Subscription;

import java.util.List;

/**
 * Interfața care definește operațiile disponibile pentru gestionarea abonamentelor în sistem.
 * Aceste operații includ obținerea tuturor abonamentelor, obținerea unui abonament după ID,
 * crearea, actualizarea și ștergerea abonamentelor, precum și obținerea abonamentelor pentru un anumit utilizator.
 */
public interface SubscriptionService {
    /**
     * Obține o listă cu toate abonamentele din sistem.
     *
     * @return Lista cu abonamente
     */
    List<Subscription> getAllSubscriptions();

    /**
     * Obține un abonament după ID-ul specificat.
     *
     * @param id ID-ul abonamentului căutat
     * @return Abonamentul cu ID-ul specificat sau null dacă nu există
     */
    Subscription getSubscriptionById(Long id);

    /**
     * Creează un abonament nou în sistem.
     *
     * @param subscription Abonamentul care urmează să fie creat
     * @return Abonamentul creat
     */
    Subscription createSubscription(Subscription subscription);

    /**
     * Actualizează un abonament existent în sistem.
     *
     * @param id                 ID-ul abonamentului care urmează să fie actualizat
     * @param updateSubscription Abonamentul actualizat
     * @return Abonamentul actualizat sau null dacă nu există un abonament cu ID-ul specificat
     */
    Subscription updateSubscription(Long id, Subscription updateSubscription);

    /**
     * Șterge un abonament existent din sistem.
     *
     * @param id ID-ul abonamentului care urmează să fie șters
     */
    void deleteSubscription(Long id);

    /**
     * Obține o listă cu abonamentele asociate unui anumit utilizator.
     *
     * @param userId ID-ul utilizatorului pentru care se caută abonamentele
     * @return Lista cu abonamentele asociate utilizatorului specificat
     */
    List<Subscription> getSubscriptionsByUserId(Long userId);
}