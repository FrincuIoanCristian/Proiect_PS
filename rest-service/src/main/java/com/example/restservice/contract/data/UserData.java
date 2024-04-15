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
     * Returnează o listă cu toți utilizatorii din sistem.
     *
     * @return Lista cu utilizatori
     */
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Returnează utilizatorul cu ID-ul specificat.
     *
     * @param id ID-ul utilizatorului căutat
     * @return Utilizatorul cu ID-ul specificat încapsulat într-un obiect Optional sau Optional.empty() dacă nu există
     */
    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    /**
     * Salvează sau actualizează un utilizator în baza de date.
     *
     * @param user Utilizatorul care urmează să fie salvat sau actualizat
     * @return Utilizatorul salvat sau actualizat
     */
    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Șterge utilizatorul cu ID-ul specificat din sistem.
     *
     * @param userId ID-ul utilizatorului care urmează să fie șters
     */
    @Override
    public void deleteById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    /**
     * Returnează utilizatorul cu numele de utilizator specificat.
     *
     * @param username Numele de utilizator căutat
     * @return Utilizatorul cu numele de utilizator specificat sau null dacă nu există
     */
    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
