package com.example.restservice.service;

import com.example.restservice.contract.ImageContract;
import com.example.restservice.model.Image;
import com.example.restservice.service.serviceImpl.ImageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ImageServiceTest {
    @Mock
    private ImageContract imageContractMock;
    private ImageService imageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(imageContractMock);
    }

    @Test
    public void getAllImagesTest() {
        List<Image> images = new ArrayList<>();
        images.add(new Image(1L, null, "url1"));
        images.add(new Image(2L, null, "url2"));
        Mockito.when(imageContractMock.findAll()).thenReturn(images);
        List<Image> allImages = imageService.getAllImages();
        Mockito.verify(imageContractMock, Mockito.times(1)).findAll();
        assertEquals(images, allImages);
    }

    @Test
    public void getImageByIdTest() {
        Long imageId = 1L;
        Image image = new Image(imageId, null, "url1");
        Mockito.when(imageContractMock.findById(imageId)).thenReturn(Optional.of(image));
        Image result = imageService.getImageById(imageId);
        Mockito.verify(imageContractMock).findById(imageId);
        assertEquals(image, result);
    }

    @Test
    public void getImageByIdNotFoundTest() {
        Long imageId = 1L;
        Mockito.when(imageContractMock.findById(imageId)).thenReturn(Optional.empty());
        Image result = imageService.getImageById(imageId);
        Mockito.verify(imageContractMock).findById(imageId);
        assertNull(result);
    }

    @Test
    public void addImageTest() {
        Image image = new Image(1, null, "url1");
        Mockito.when(imageContractMock.save(image)).thenReturn(image);
        Image result = imageService.createImage(image);
        Mockito.verify(imageContractMock).save(image);
        assertEquals(image, result);
    }

    @Test
    public void updateImageTest() {
        Long imageId = 1L;
        Image existingImage = new Image(imageId, null, "url1");
        Image updatedImage = new Image(imageId, null, "url2");
        Mockito.when(imageContractMock.findById(imageId)).thenReturn(Optional.of(existingImage));
        Mockito.when(imageContractMock.save(updatedImage)).thenReturn(existingImage);
        Image result = imageService.updateImage(imageId, updatedImage);
        Mockito.verify(imageContractMock).findById(imageId);
        Mockito.verify(imageContractMock).save(updatedImage);
        assertEquals(existingImage, result);
    }

    @Test
    public void updateImageNotFoundTest() {
        Long imageId = 1L;
        Image updateImage = new Image(imageId, null, "url1");
        Mockito.when(imageContractMock.findById(imageId)).thenReturn(Optional.empty());
        Image result = imageService.updateImage(imageId, updateImage);
        Mockito.verify(imageContractMock).findById(imageId);
        assertNull(result);
    }

    @Test
    public void deleteImageTest() {
        Long imageId = 1L;
        imageService.deleteImage(imageId);
        Mockito.verify(imageContractMock).deleteById(imageId);
    }
}
