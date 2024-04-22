package com.example.restservice.repository;

import com.example.restservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfata care defineste operatiile de baza pentru gestionarea entitatilor Subscription in baza de date.
 * Extinde JpaRepository, oferind astfel metodele necesare pentru a accesa si manipula datele entitaților Subscription.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    /**
     * Metoda care obtine abonamentele asociate unui utilizator cu un ID
     * @param userId Id-ul utilizatorului pentru care se cauta abonamente
     * @return lista de abonamente
     */
    @Query("SELECT s FROM Subscription s WHERE s.user.userId = :userId")
    List<Subscription> findByUserId(Long userId);
}
