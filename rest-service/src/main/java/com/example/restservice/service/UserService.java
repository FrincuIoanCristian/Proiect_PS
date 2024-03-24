package com.example.restservice.service;

import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(long id, User newDetails){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setUsername(newDetails.getUsername());
            user.setPassword(newDetails.getPassword());
            user.setEmail(newDetails.getEmail());
            user.setFullname(newDetails.getFullname());
            user.setAvatar(newDetails.getAvatar());
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
