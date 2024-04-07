package com.example.restservice.service;

import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    /**
     * Returneaza un utilizator dupa un ID.
     *
     * @param id    ID-ul utilizatorului cautat.
     * @return  Utilizatorul cu ID-ul respectiv daca exista, altfel returneaza 'null'.
     */
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Creaza un obiect nou si il memoreaza in baza de date.
     *
     * @param user  Detaliile noului utilizator.
     * @return  Utilizatorul creat.
     */
    public User createUser(User user){
        return userRepository.save(user);
    }

    /**
     * Actualizeaza un user cu ID-ul precizat, daca acesta exista.
     *
     * @param id    ID-ul utilizatorului caruia dorim sa actualizam detaliile.
     * @param updateUser    Detaliile cu care dorim sa actualizam utilizatorul.
     * @return  Utilizatorul cu noile detalii actualizate.
     */
    public User updateUser(long id, User updateUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            updateUser.setUserId(user.getUserId());
            return userRepository.save(updateUser);
        }else{
            return null;
        }
    }

    /**
     * Sterge un utilizator existent dupa un ID.
     *
     * @param id    ID-ul utilizatoului ce trebuie sters.
     */
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
