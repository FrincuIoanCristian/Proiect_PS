package com.example.restservice.controller;

import com.example.restservice.model.Image;
import com.example.restservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller-ul responsabil pentru gestionarea operatiilor legate de imagini.
 * Acesta expune API-uri REST pentru a obtine, crea, actualiza si sterge imagini.
 * /images (GET)
 * /images/{id} (GET)
 * /images (POST)
 * /images/{id} (PUT)
 * /images/{id} (DELETE)
 * /imagesgetImagesByNewsId/{id} (GET)
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Obtine toate imaginile.
     *
     * @return Lista de imagini
     */
    @GetMapping
    public List<Image> getImages() {
        return imageService.getAllImages();
    }

    /**
     * Obtine o imagine dupa ID.
     *
     * @param id ID-ul imaginii cautate
     * @return ResponseEntity conținand imaginea gasita sau HttpStatus.NOT_FOUND daca imaginea nu exista
     */
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creeaza o imagine noua.
     *
     * @param image Detaliile imaginii care urmeaza sa fie create
     * @return ResponseEntity continand imaginea creata si HttpStatus.CREATED
     */
    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        Image savedImage = imageService.createImage(image);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza o imagine existenta.
     *
     * @param id    ID-ul imaginii care urmeaza sa fie actualizata
     * @param image Detaliile actualizate ale imaginii
     * @return ResponseEntity conținand imaginea actualizata sau HttpStatus.NOT_FOUND daca imaginea nu exista
     */
    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable long id, @RequestBody Image image) {
        Image updateImage = imageService.updateImage(id, image);
        if (updateImage != null) {
            return new ResponseEntity<>(updateImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge o imagine după ID.
     *
     * @param id ID-ul imaginii care urmeaza să fie stearsa
     * @return ResponseEntity cu HttpStatus.OK pentru confirmarea stergerii
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable long id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obtine toate imaginile asociate unei stiri
     * @param id ID-ul stirii
     * @return lista de imagini
     */
    @GetMapping("/getImagesByNewsId/{id}")
    public ResponseEntity<List<Image>> getImagesByNewsId(@PathVariable long id) {
        List<Image> images = imageService.getAllImagesByNewsID(id);
        if(images != null) {
            return new ResponseEntity<>(images, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
