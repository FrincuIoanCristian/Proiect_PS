package com.example.restservice.repository;

import com.example.restservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfata care defineste operatiile de baza pentru gestionarea entitatilor Category in baza de date.
 * Extinde JpaRepository, oferind astfel metodele necesare pentru a accesa si manipula datele entitaților Category.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}