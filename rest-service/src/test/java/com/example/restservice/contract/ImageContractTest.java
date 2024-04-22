package com.example.restservice.contract;

import com.example.restservice.contract.data.ImageData;
import com.example.restservice.model.Image;
import com.example.restservice.repository.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Teste pentru clasa ImageContract.
 */
public class ImageContractTest {
    @Mock
    private ImageRepository imageRepositoryMock;
    private ImageContract imageContract;

    /**
     * Initializarea testelor.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageContract = new ImageData(imageRepositoryMock);
    }

    /**
     * Test pentru metoda findAll.
     */
    @Test
    public void findAllTest() {
        List<Image> images = new ArrayList<>();
        images.add(new Image(1L, null, "url1"));
        images.add(new Image(2L, null, "url2"));
        when(imageRepositoryMock.findAll()).thenReturn(images);
        List<Image> result = imageContract.findAll();
        verify(imageRepositoryMock).findAll();
        assertEquals(images, result);
    }

    /**
     * Test pentru metoda findById, cand il gaseste
     */
    @Test
    public void findByIdFoundTest() {
        Long imageId = 1L;
        Image image = new Image(imageId, null, "url1");
        when(imageRepositoryMock.findById(imageId)).thenReturn(Optional.of(image));
        Optional<Image> result = imageContract.findById(imageId);
        verify(imageRepositoryMock).findById(imageId);
        assertEquals(Optional.of(image), result);
    }

    /**
     * Test pentru metoda findById, cand nu il gaseste.
     */
    @Test
    public void findByIdNotFoundTest() {
        Long imageId = 1L;
        when(imageRepositoryMock.findById(imageId)).thenReturn(Optional.empty());
        Optional<Image> result = imageContract.findById(imageId);
        verify(imageRepositoryMock).findById(imageId);
        assertEquals(Optional.empty(), result);
    }

    /**
     * Test pentru metoda save.
     */
    @Test
    public void saveTest() {
        Image image = new Image(1, null, "url1");
        when(imageRepositoryMock.save(image)).thenReturn(image);
        Image result = imageContract.save(image);
        verify(imageRepositoryMock).save(image);
        assertEquals(image, result);
    }

    /**
     * Test pentru metoda deleteById.
     */
    @Test
    public void deleteByIdTest() {
        Long imageId = 1L;
        imageContract.deleteById(imageId);
        verify(imageRepositoryMock).deleteById(imageId);
    }

    /**
     * Test pentru metoda findByNewsId.
     */
    @Test
    public void findByNewsIDTest() {
        Long newsId = 1L;
        List<Image> images = new ArrayList<>();
        images.add(new Image(1L, null, "url1"));
        images.add(new Image(2L, null, "url2"));
        when(imageRepositoryMock.findByNewsID(newsId)).thenReturn(images);
        List<Image> result = imageContract.findByNewsID(newsId);
        verify(imageRepositoryMock).findByNewsID(newsId);
        assertEquals(images, result);
    }
}
