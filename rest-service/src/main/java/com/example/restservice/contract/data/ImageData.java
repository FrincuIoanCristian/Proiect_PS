package com.example.restservice.contract.data;

import com.example.restservice.contract.ImageContract;
import com.example.restservice.model.Image;
import com.example.restservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementarea contractului pentru gestionarea datelor imaginilor în baza de date.
 */
@Repository
public class ImageData implements ImageContract {
    /**
     * Utilizez repozitory-ul JPA pentru lucrul cu baza de date pentru tabela Category
     */
    private final ImageRepository imageRepository;

    /**
     * Constructorul care injectează dependența către ImageRepository.
     *
     * @param imageRepository Repository-ul pentru gestionarea entităților Image în baza de date
     */
    @Autowired
    public ImageData(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Metoda care cauta toate imaginilor
     *
     * @return lista de imagini
     */
    @Override
    public List<Image> findAll() {
        return this.imageRepository.findAll();
    }

    /**
     * Metoda care cauta o imagine dupa ID
     *
     * @param id ID-ul imaginii cautate
     * @return Abonamentul gasit sau null
     */
    @Override
    public Optional<Image> findById(Long id) {
        return this.imageRepository.findById(id);
    }

    /**
     * Metoda care salveaza imaginea in baza de date
     *
     * @param image Detaliile imaginii ce vreau sa o salvez
     * @return Imaginea salvata
     */
    @Override
    public Image save(Image image) {
        return this.imageRepository.save(image);
    }

    /**
     * Metoda care sterge o imagine
     *
     * @param id ID-ul imaginii ce urmeaza sa fie stearsa
     */
    @Override
    public void deleteById(Long id) {
        this.imageRepository.deleteById(id);
    }

    /**
     * Metoda care cauta toate imaginile unei stiri
     *
     * @param newsID ID-ul stirii
     * @return lista de imagini
     */
    @Override
    public List<Image> findByNewsID(Long newsID) {
        return imageRepository.findByNewsID(newsID);
    }
}
