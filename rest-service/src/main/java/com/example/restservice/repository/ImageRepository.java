package com.example.restservice.repository;

import com.example.restservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfata care defineste operatiile de baza pentru gestionarea entitatilor Image in baza de date.
 * Extinde JpaRepository, oferind astfel metodele necesare pentru a accesa si manipula datele entitaților Image.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    /**
     * Metoda care obtine imaginile asociate unei stiri cu un ID
     * @param newsId ID-ul stirii pentru care se cauta imagini
     * @return lista de imagini
     */
    @Query("SELECT i FROM Image i WHERE i.news.newsId = :newsId")
    List<Image> findByNewsID(Long newsId);
}
