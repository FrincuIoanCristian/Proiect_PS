package com.example.restservice.service;

import com.example.restservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long id,User updateUser);
    void deleteUser(Long userId);
    User getUserByUsername(String username);
}
