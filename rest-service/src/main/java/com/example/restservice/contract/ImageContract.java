package com.example.restservice.contract;

import com.example.restservice.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageContract {
    List<Image> findAll();

    Optional<Image> findById(Long id);

    Image save(Image image);

    void deleteById(Long id);
}
