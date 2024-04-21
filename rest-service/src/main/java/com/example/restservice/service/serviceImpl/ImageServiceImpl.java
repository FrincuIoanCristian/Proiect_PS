package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.ImageContract;
import com.example.restservice.model.Image;
import com.example.restservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageContract imageContract;

    @Autowired
    public ImageServiceImpl(ImageContract imageContract) {
        this.imageContract = imageContract;
    }

    @Override
    public List<Image> getAllImages() {
        return imageContract.findAll();
    }

    @Override
    public Image getImageById(Long id) {
        return imageContract.findById(id).orElse(null);
    }

    @Override
    public Image createImage(Image image) {
        return imageContract.save(image);
    }

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


    @Override
    public void deleteImage(Long id) {
        imageContract.deleteById(id);
    }
}
