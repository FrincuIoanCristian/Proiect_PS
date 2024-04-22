package com.example.restservice.contract.data;

import com.example.restservice.contract.UserContract;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementarea contractului pentru gestionarea datelor utilizatorilor în baza de date.
 */
@Repository
public class UserData implements UserContract {
    /**
     * Utilizez repozitory-ul JPA pentru lucrul cu baza de date pentru tabela User
     */
    private final UserRepository userRepository;

    /**
     * Constructorul care injectează dependența către UserRepository.
     *
     * @param userRepository Repository-ul pentru gestionarea entităților User în baza de date
     */
    @Autowired
    public UserData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Metoda care cauta toti utilizatori
     *
     * @return lista de utilizatori
     */
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Metoda care cauta un utilizator dupa ID
     *
     * @param id ID-ul utilizatorului cautat
     * @return Utilizatorul gasit sau null
     */
    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    /**
     * Metoda care salveaza utilizatorul in baza de date
     *
     * @param user Detaliile utilizatorului ce vreau sa il salvez
     * @return Utilizatorul salvat
     */
    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Metoda care sterge un utilizator
     *
     * @param userId Id-ul utilizatorului ce urmeaza sa il sterg
     */
    @Override
    public void deleteById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    /**
     * Metoda care cauta un utilizator dupa username
     *
     * @param username Username-ul utilizatorului cautat
     * @return Utilizatorul gasit
     */
    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
