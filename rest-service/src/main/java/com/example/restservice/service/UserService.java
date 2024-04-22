package com.example.restservice.service;

import com.example.restservice.model.User;

import java.util.List;

/**
 * Interfața care definește operațiile disponibile pentru gestionarea utilizatorilor în sistem.
 * Aceste operații includ obținerea tuturor utilizatorilor, obținerea unui utilizator după ID,
 * crearea, actualizarea și ștergerea utilizatorilor, precum și obținerea unui utilizator după numele de utilizator.
 */
public interface UserService {
    /**
     * Obține o listă cu toți utilizatorii din sistem.
     *
     * @return Lista cu utilizatori
     */
    List<User> getAllUsers();

    /**
     * Obține un utilizator după ID-ul specificat.
     *
     * @param userId ID-ul utilizatorului căutat
     * @return Utilizatorul cu ID-ul specificat sau null dacă nu există
     */
    User getUserById(Long userId);

    /**
     * Creează un utilizator nou în sistem.
     *
     * @param user Utilizatorul care urmează să fie creat
     * @return Utilizatorul creat
     */
    User createUser(User user);

    /**
     * Actualizează un utilizator existent în sistem.
     *
     * @param id         ID-ul utilizatorului care urmează să fie actualizat
     * @param updateUser Utilizatorul actualizat
     * @return Utilizatorul actualizat sau null dacă nu există un utilizator cu ID-ul specificat
     */
    User updateUser(Long id, User updateUser);

    /**
     * Șterge un utilizator existent din sistem.
     *
     * @param userId ID-ul utilizatorului care urmează să fie șters
     */
    void deleteUser(Long userId);

    /**
     * Obține un utilizator după numele de utilizator specificat.
     *
     * @param username Numele de utilizator căutat
     * @return Utilizatorul cu numele de utilizator specificat sau null dacă nu există
     */
    User getUserByUsername(String username);
}
