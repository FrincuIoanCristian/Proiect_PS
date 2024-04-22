package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.UserContract;
import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementarea contractului pentru gestionarea datelor utilizatorilor în baza de date.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserContract userContract;

    /**
     * Constructorul care injectează dependența către UserContract
     *
     * @param userContract Contractul pentru gestionarea datelor de tipul User
     */
    @Autowired
    public UserServiceImpl(UserContract userContract) {
        this.userContract = userContract;
    }

    /**
     * Metoda care obține o listă cu toți utilizatorii din sistem.
     *
     * @return Lista cu utilizatori
     */
    @Override
    public List<User> getAllUsers() {
        return userContract.findAll();
    }

    /**
     * Metoda care obține un utilizator după ID-ul specificat.
     *
     * @param userId ID-ul utilizatorului căutat
     * @return Utilizatorul cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public User getUserById(Long userId) {
        return userContract.findById(userId).orElse(null);
    }

    /**
     * Metoda care creează un utilizator nou în sistem.
     *
     * @param user Utilizatorul care urmează să fie creat
     * @return Utilizatorul creat
     */
    @Override
    public User createUser(User user) {
        return userContract.save(user);
    }


    /**
     * Metoda care actualizează un utilizator existent în sistem.
     *
     * @param id         ID-ul utilizatorului care urmează să fie actualizat
     * @param updateUser Utilizatorul actualizat
     * @return Utilizatorul actualizat sau null dacă nu există un utilizator cu ID-ul specificat
     */
    @Override
    public User updateUser(Long id, User updateUser) {
        User user = userContract.findById(id).orElse(null);
        if (user != null) {
            updateUser.setUserId(user.getUserId());
            return userContract.save(updateUser);
        } else {
            return null;
        }
    }

    /**
     * Metoda care sterge un utilizator existent din sistem.
     *
     * @param userId ID-ul utilizatorului care urmează să fie șters
     */
    @Override
    public void deleteUser(Long userId) {
        userContract.deleteById(userId);
    }

    /**
     * Metoda care obține un utilizator după numele de utilizator specificat.
     *
     * @param username Numele de utilizator căutat
     * @return Utilizatorul cu numele de utilizator specificat sau null dacă nu există
     */
    @Override
    public User getUserByUsername(String username) {
        return userContract.findByUsername(username);
    }

}
