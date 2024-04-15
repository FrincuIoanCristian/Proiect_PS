package com.example.restservice.service.serviceImpl;

import com.example.restservice.contract.UserContract;
import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementarea serviciului pentru gestionarea utilizatorilor în sistem.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserContract userContract;
    @Autowired
    public UserServiceImpl(UserContract userContract) {
        this.userContract = userContract;
    }

    /**
     * Metoda care returneaza o listă cu toți utilizatorii din sistem.
     *
     * @return Lista cu utilizatori
     */
    @Override
    public List<User> getAllUsers() {
        return userContract.findAll();
    }

    /**
     * Metoda care cauta utilizatorul cu ID-ul specificat.
     *
     * @param userId ID-ul utilizatorului căutat
     * @return Utilizatorul cu ID-ul specificat sau null dacă nu există
     */
    @Override
    public User getUserById(Long userId) {
        return userContract.findById(userId).orElse(null);
    }

    /**
     * Metoda care creaza un utilizator nou în sistem.
     *
     * @param user Utilizatorul care urmează să fie creat
     * @return Utilizatorul creat
     */
    @Override
    public User createUser(User user) {
        return userContract.save(user);
    }


    /**
     * Metoda care actualizeaza un utilizator existent în sistem.
     *
     * @param id ID-ul utilizatorului care urmează să fie actualizat
     * @param updateUser Utilizatorul actualizat
     * @return Utilizatorul actualizat sau null dacă nu există un utilizator cu ID-ul specificat
     */
    @Override
    public User updateUser(Long id,User updateUser) {
        User user = userContract.findById(id).orElse(null);
        if(user != null){
            updateUser.setUserId(user.getUserId());
            return userContract.save(updateUser);
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
        userContract.deleteById(userId);
    }

    /**
     * Returneaza un utilizator dupa username
     * @param username usename-ul utilizatorului cautat
     * @return utilizatorul gasit
     */
    @Override
    public User getUserByUsername(String username) {
        return userContract.findByUsername(username);
    }

}
