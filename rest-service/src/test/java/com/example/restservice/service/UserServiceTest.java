package com.example.restservice.service;

import com.example.restservice.contract.UserContract;
import com.example.restservice.model.User;
import com.example.restservice.service.serviceImpl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;


public class UserServiceTest {
    @Mock
    private UserContract contractMock;
    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(contractMock);
    }
    @Test
    public void saveUserTest(){
        User user = new User(1,"bony", "1234", "bony@gmail.com", "Alexandru Bonyhai", "", 100.0);
        Mockito.when(contractMock.save(user)).thenReturn(user);
        User result = userService.createUser(user);
        Mockito.verify(contractMock).save(user);
        assertEquals(user,result);
    }
}
