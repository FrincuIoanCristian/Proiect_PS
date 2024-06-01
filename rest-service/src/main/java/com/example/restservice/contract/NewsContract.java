package com.example.restservice.contract;

import com.example.restservice.model.News;
import com.example.restservice.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfata care definește operatiile de baza pentru gestionarea stirilor in aplicatie.
 * Aceste operatii includ gasirea, salvarea, actualizarea si stergerea stirilor.
 */
public interface NewsContract {
    /**
     * Cauta toate stirile
     *
     * @return lista de stiri
     */
    List<News> findAll();

    /**
     * Cauta o stire dupa ID
     *
     * @param id ID-ul stirii cautate
     * @return Stirea gasita sau null
     */
    Optional<News> findById(Long id);

    /**
     * Salveaza stirea in baza de date
     *
     * @param news Detaliile stirii ce vreau sa o salvez
     * @return Stirea salvat
     */
    News save(News news);

    /**
     * Sterge o stire
     *
     * @param id ID-ul stirii ce urmeaza sa fie stearsa
     */
    void deleteById(Long id);

    /**
     * Cauta toate stirile dupa numele categoriei din care face parte
     *
     * @param categoryName Numele categoriei
     * @return lista de stiri
     */
    List<News> findByCategoryName(String categoryName);

    /**
     * Cauta toti utilizatori ce sunt abonati la categoria de care face parte stirea
     *
     * @param newsId Id-ul stirii
     * @return lista de utilizatori
     */
    List<User> findUsersByNewsId(Long newsId);

    List<News> findTop3ByOrderByPublishedAtDesc(Pageable pageable);
}
