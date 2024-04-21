package com.example.restservice.service;

import com.example.restservice.contract.UserContract;
import com.example.restservice.model.User;
import com.example.restservice.service.serviceImpl.UserServiceImpl;
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


public class UserServiceTest {
    @Mock
    private UserContract userContractMock;
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userContractMock);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "username1", "password1", "email1", "fullName1", "avatar1", 100.0));
        users.add(new User(2, "username2", "password2", "email2", "fullName2", "avatar2", 200.0));
        Mockito.when(userContractMock.findAll()).thenReturn(users);
        List<User> allUsers = userService.getAllUsers();
        Mockito.verify(userContractMock).findAll();
        assertEquals(users, allUsers);
    }

    @Test
    public void getUserByIdTest() {
        Long userId = 1L;
        User user = new User(1, "username", "password", "email", "fullName", "avatar", 100.0);
        Mockito.when(userContractMock.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        Mockito.verify(userContractMock).findById(userId);
        assertEquals(user, result);
    }

    @Test
    public void getUserByIdNotFoundTest() {
        Long userId = 1L;
        Mockito.when(userContractMock.findById(userId)).thenReturn(Optional.empty());
        User result = userService.getUserById(userId);
        Mockito.verify(userContractMock).findById(userId);
        assertNull(result);
    }

    @Test
    public void addUserTest() {
        User user = new User(1, "username", "password", "email", "fullName", "avatar", 100.0);
        Mockito.when(userContractMock.save(user)).thenReturn(user);
        User result = userService.createUser(user);
        Mockito.verify(userContractMock).save(user);
        assertEquals(user, result);
    }

    @Test
    public void updateUserTest() {
        Long userId = 1L;
        User existingUser = new User(1, "username", "password", "email", "fullName", "avatar", 100.0);
        User updatedUser = new User(1, "updatedUsername", "updatedPassword", "updatedEmail", "updatedFullName", "updatedAvatar", 200.0);
        Mockito.when(userContractMock.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userContractMock.save(updatedUser)).thenReturn(existingUser);
        User result = userService.updateUser(userId, updatedUser);
        Mockito.verify(userContractMock).findById(userId);
        Mockito.verify(userContractMock).save(updatedUser);
        assertEquals(existingUser, result);
    }

    @Test
    public void updateUserNotFoundTest() {
        Long userId = 1L;
        User updatedUser = new User(userId, "updatedUsername", "updatedPassword", "updatedEmail", "updatedFullName", "updatedAvatar", 200.0);
        Mockito.when(userContractMock.findById(userId)).thenReturn(Optional.empty());
        User result = userService.updateUser(userId, updatedUser);
        Mockito.verify(userContractMock).findById(userId);
        assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.deleteUser(userId);
        Mockito.verify(userContractMock).deleteById(userId);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "username";
        User user = new User(1, username, "password", "email", "fullName", "avatar", 100.0);
        Mockito.when(userContractMock.findByUsername(username)).thenReturn(user);
        User result = userService.getUserByUsername(username);
        Mockito.verify(userContractMock).findByUsername(username);
        assertEquals(user, result);
    }
}
