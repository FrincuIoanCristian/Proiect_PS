package com.example.restservice.contract;

import com.example.restservice.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interfața care definește operațiile de bază pentru gestionarea utilizatorilor în baza de date.
 */
public interface UserContract {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long userId);

    User findByUsername(String username);
}
