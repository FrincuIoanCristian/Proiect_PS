package com.example.restservice.contract;

import com.example.restservice.model.Subscription;

import java.util.List;
import java.util.Optional;

/**
 * Interfata care definește operatiile de baza pentru gestionarea abonamentelor in aplicatie.
 * Aceste operatii includ gasirea, salvarea, actualizarea si stergerea abonamentelor.
 */
public interface SubscriptionContract {
    /**
     * Cauta toate abonamentele
     *
     * @return lista de abonamente
     */
    List<Subscription> findAll();

    /**
     * Cauta un abonament dupa ID
     *
     * @param id ID-ul abonamentului cautat
     * @return Abonamentul gasit sau null
     */
    Optional<Subscription> findById(Long id);

    /**
     * Salveaza abonamentul in baza de date
     *
     * @param subscription Detaliile abonamentului ce vreau sa il salvez
     * @return Abonamentul salvat
     */
    Subscription save(Subscription subscription);

    /**
     * Sterge un abonament
     *
     * @param id ID-ul abonamentului ce urmeaza sa fie sters
     */
    void deleteById(Long id);

    /**
     * Cauta toate abonametele dupa un ID al utilizatorului
     *
     * @param userId ID-ul utilizatorului
     * @return Lista de abonamente
     */
    List<Subscription> findByUserId(Long userId);
}
