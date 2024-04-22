package com.example.restservice.contract;

import com.example.restservice.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interfata care definește operatiile de baza pentru gestionarea utilozatorilor in aplicatie.
 * Aceste operatii includ gasirea, salvarea, actualizarea si stergerea utilizatorilor.
 */
public interface UserContract {
    /**
     * Cauta toti utilizatori
     *
     * @return lista de utilizatori
     */
    List<User> findAll();

    /**
     * Cauta un utilizator dupa ID
     *
     * @param id ID-ul utilizatorului cautat
     * @return Utilizatorul gasit sau null
     */
    Optional<User> findById(Long id);

    /**
     * Salveaza utilizatorul in baza de date
     *
     * @param user Detaliile utilizatorului ce vreau sa il salvez
     * @return Utilizatorul salvat
     */
    User save(User user);

    /**
     * Sterge un utilizator
     *
     * @param userId Id-ul utilizatorului ce urmeaza sa il sterg
     */
    void deleteById(Long userId);

    /**
     * Cauta un utilizator dupa username
     *
     * @param username Username-ul utilizatorului cautat
     * @return Utilizatorul gasit
     */
    User findByUsername(String username);
}
