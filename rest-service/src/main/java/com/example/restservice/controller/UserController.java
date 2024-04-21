package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtine toti utilizatori.
     *
     * @return o lista cu toti utilizatori
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Obtine un utilizator dupa un ID.
     *
     * @param id Id-ul utilizatorului cautat.
     * @return Un obiect ResponseEntity care contine utilizatorul si mesajul OK, sau un mesaj de eroare in caz ca nu exista.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza un utilizator nou.
     *
     * @param user Detaliile noului utilizator.
     * @return Un obiect ResponseEntity care contine utilizatorul creat si statusul HTTP corespunzator. Detaliile vor fi scrise in Body.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza un utilizator existent.
     * Se verifica cu URL-ul 'http://localhost:8080/users/{id}', iar in loc de {id} se pune ID-ul urilizatorului dorit. Detaliile vor fi scrise in Body.
     *
     * @param id         Id-ul utilizatorului care trebuie actualizat.
     * @param newDetails Detaliile de actualizare a utilizatorului.
     * @return Un obiect ResponseEntity care contine utilizatorul si mesajul OK, sau un mesaj de eroare in caz ca nu exista.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User newDetails) {
        User updateUser = userService.updateUser(id, newDetails);
        if (updateUser != null) {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge un utilizator existent dupa un anumit ID.
     *
     * @param id ID-ul utilizatoruluo care trebuie sters.
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint pentru login, primesc un username si o parola si caut utilizatorul cu userul repectiv si verific daca exista
     * si parolele sunt egale
     *
     * @param user contine username-ul si parola pentru care doresc sa fac login
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Autentificare reușită", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Autentificare eșuată", HttpStatus.UNAUTHORIZED);
        }
    }

}
