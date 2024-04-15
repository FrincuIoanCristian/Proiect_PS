package com.example.restservice.repository;

import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfața care definește operațiile de bază pentru gestionarea entităților User în baza de date.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Metoda care găsește un utilizator după username.
     *
     * @param username Username-ul utilizatorului căutat
     * @return Utilizatorul găsit sau null dacă nu există un utilizator cu acel username
     */
    User findByUsername(String username);
}