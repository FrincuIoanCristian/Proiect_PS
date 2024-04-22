package com.example.restservice.repository;

import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfata care defineste operatiile de baza pentru gestionarea entitatilor User in baza de date.
 * Extinde JpaRepository, oferind astfel metodele necesare pentru a accesa si manipula datele entitaților User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Metoda care găsește un utilizator după username.
     *
     * @param username Username-ul utilizatorului căutat
     * @return utilizatorul găsit sau null dacă nu există un utilizator cu acel username
     */
    User findByUsername(String username);
}