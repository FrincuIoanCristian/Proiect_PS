package com.example.restservice.contract.data;

import com.example.restservice.contract.ImageContract;
import com.example.restservice.model.Image;
import com.example.restservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImageData implements ImageContract {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageData(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAll() {
        return this.imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return this.imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return this.imageRepository.save(image);
    }

    @Override
    public void deleteById(Long id) {
        this.imageRepository.deleteById(id);
    }
}
