package com.example.restservice.contract;

import com.example.restservice.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Interfata care definește operatiile de baza pentru gestionarea categoriilor in aplicatie.
 * Aceste operatii includ gasirea, salvarea, actualizarea si stergerea categoriilor.
 */
public interface CategoryContract {
    /**
     * Cauta toate categoriile
     *
     * @return lista de categorii
     */
    List<Category> findAll();

    /**
     * Cauta o categorie dupa ID
     *
     * @param id ID-ul categoriei cautate
     * @return Categoria gasita sau null
     */
    Optional<Category> findById(Long id);

    /**
     * Salveaza categoriei in baza de date
     *
     * @param category Detaliile categoriei ce vreau sa o salvez
     * @return Categoria salvata
     */
    Category save(Category category);

    /**
     * Sterge o categorie
     *
     * @param id ID-ul categoriei ce urmeaza sa fie stearsa
     */
    void deleteById(Long id);
}
