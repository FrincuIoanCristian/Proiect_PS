package com.example.restservice.service;

import com.example.restservice.model.Image;

import java.util.List;

/**
 * Interfața care definește operațiile disponibile pentru gestionarea imaginilor în sistem.
 * Aceste operații includ obținerea tuturor imaginilor, obținerea unei imagini după ID,
 * crearea, actualizarea și ștergerea imaginilor.
 */
public interface ImageService {
    /**
     * Obține o listă cu toate imaginile din sistem.
     *
     * @return Lista cu imagini
     */
    List<Image> getAllImages();

    /**
     * Obține o imagine după ID-ul specificat.
     *
     * @param id ID-ul imaginii căutate
     * @return Imaginea cu ID-ul specificat sau null dacă nu există
     */
    Image getImageById(Long id);

    /**
     * Creează o imagine nouă în sistem.
     *
     * @param image Imaginea care urmează să fie creată
     * @return Imaginea creată
     */
    Image createImage(Image image);

    /**
     * Actualizează o imagine existentă în sistem.
     *
     * @param id          ID-ul imaginii care urmează să fie actualizată
     * @param updateImage Imaginea actualizată
     * @return Imaginea actualizată sau null dacă nu există o imagine cu ID-ul specificat
     */
    Image updateImage(Long id, Image updateImage);

    /**
     * Șterge o imagine existentă din sistem.
     *
     * @param id ID-ul imaginii care urmează să fie ștearsă
     */
    void deleteImage(Long id);

    /**
     * Obtine toate imaginile unei stiri
     * @param newsID id-ul stirii cautate
     * @return lista de imagini
     */
    List<Image> getAllImagesByNewsID(Long newsID);
}