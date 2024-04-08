package com.example.restservice.serviceImpl;

import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returneaza toti utilizatori.
     *
     * @return lista cu toti utilizatori
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returneaza un utilizator dupa un ID.
     *
     * @param userId ID-ul utilizatorului cautat.
     * @return Utilizatorul cu ID-ul respectiv daca exista, altfel returneaza 'null'.
     */
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * Creaza un obiect nou si il memoreaza in baza de date.
     *
     * @param user  Detaliile noului utilizator.
     * @return  Utilizatorul creat.
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }


    /**
     * Actualizeaza un user cu ID-ul precizat, daca acesta exista.
     *
     * @param id    ID-ul utilizatorului caruia dorim sa actualizam detaliile.
     * @param updateUser    Detaliile cu care dorim sa actualizam utilizatorul.
     * @return  Utilizatorul cu noile detalii actualizate.
     */
    @Override
    public User updateUser(Long id,User updateUser) {
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
     * @param userId    ID-ul utilizatoului ce trebuie sters.
     */
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Returneaza un utilizator dupa username
     * @param username usename-ul utilizatorului cautat
     * @return utilizatorul gasit
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
