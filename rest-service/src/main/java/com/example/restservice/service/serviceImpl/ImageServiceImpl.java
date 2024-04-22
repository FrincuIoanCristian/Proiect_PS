package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.ImageContract;
import com.example.restservice.model.Image;
import com.example.restservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementarea serviciului pentru gestionarea imaginilor în sistem.
 */
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageContract imageContract;

    /**
     * Constructorul care injectează dependența către ImageContract
     *
     * @param imageContract Contractul pentru gestionarea datelor de tipul Image
     */
    @Autowired
    public ImageServiceImpl(ImageContract imageContract) {
        this.imageContract = imageContract;
    }

    /**
     * Metoda care obține o listă cu toate imaginile din sistem.
     *
     * @return Lista cu imagini
     */
    @Override
    public List<Image> getAllImages() {
        return imageContract.findAll();
    }

    /**
     * Metoda care obține o imagine după ID-ul specificat.
     *
     * @param id ID-ul imaginii căutate
     * @return Imaginea cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public Image getImageById(Long id) {
        return imageContract.findById(id).orElse(null);
    }

    /**
     * Metoda care creează o imagine nouă în sistem.
     *
     * @param image Imaginea care urmează să fie creată
     * @return Imaginea creată
     */
    @Override
    public Image createImage(Image image) {
        return imageContract.save(image);
    }

    /**
     * Metoda care actualizează o imagine existentă în sistem.
     *
     * @param id          ID-ul imaginii care urmează să fie actualizată
     * @param updateImage Imaginea actualizată
     * @return Imaginea actualizată sau null dacă nu există o imagine cu ID-ul specificat
     */
    @Override
    public Image updateImage(Long id, Image updateImage) {
        Image image = imageContract.findById(id).orElse(null);
        if (image != null) {
            updateImage.setImageId(image.getImageId());
            return imageContract.save(updateImage);
        } else {
            return null;
        }
    }

    /**
     * Metoda care sterge o imagine existentă din sistem.
     *
     * @param id ID-ul imaginii care urmează să fie ștearsă
     */
    @Override
    public void deleteImage(Long id) {
        imageContract.deleteById(id);
    }

    /**
     * Metoda care obtine toate imaginile unei stiri
     * @param newsID id-ul stirii cautate
     * @return lista de imagini
     */
    @Override
    public List<Image> getAllImagesByNewsID(Long newsID) {
        return imageContract.findByNewsID(newsID);
    }
}
