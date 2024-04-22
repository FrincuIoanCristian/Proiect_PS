package com.example.restservice.contract;

import com.example.restservice.model.Image;

import java.util.List;
import java.util.Optional;

/**
 * Interfata care definește operatiile de baza pentru gestionarea imaginilor in aplicatie.
 * Aceste operatii includ gasirea, salvarea, actualizarea si stergerea imaginilor.
 */
public interface ImageContract {
    /**
     * Cauta toate imaginilor
     *
     * @return lista de imagini
     */
    List<Image> findAll();

    /**
     * Cauta o imagine dupa ID
     *
     * @param id ID-ul imaginii cautate
     * @return Abonamentul gasit sau null
     */
    Optional<Image> findById(Long id);

    /**
     * Salveaza imaginea in baza de date
     *
     * @param image Detaliile imaginii ce vreau sa o salvez
     * @return Imaginea salvata
     */
    Image save(Image image);

    /**
     * Sterge o imagine
     *
     * @param id ID-ul imaginii ce urmeaza sa fie stearsa
     */
    void deleteById(Long id);

    /**
     * Cauta toate imaginile unei stiri
     *
     * @param newsID ID-ul stirii
     * @return lista de imagini
     */
    List<Image> findByNewsID(Long newsID);
}
