package com.example.restservice.service;

import com.example.restservice.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> getAllImages();

    Image getImageById(long id);

    Image createImage(Image image);

    Image updateImage(Long id, Image updateImage);

    void deleteImage(long id);

}
