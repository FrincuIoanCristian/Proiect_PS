package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller-ul responsabil pentru gestionarea operatiilor legate de utilizatori.
 * Acesta expune API-uri REST pentru a obtine, crea, actualiza si sterge utilizatori.
 * /users (GET)
 * /users/{id} (GET)
 * /users (POST)
 * /users/{id} (PUT)
 * /users/{id} (DELETE)
 * /users/login (POST)
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
     * @return lista de utilizari
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Obtine un utilizator dupa ID.
     *
     * @param id Id-ul utilizatorului cautat.
     * @return ResponseEntity conținand utilizatorul gasit sau HttpStatus.NOT_FOUND daca utilizatorul nu exista
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
     * @param user Detaliile utilizatorului care urmeaza să fie creat
     * @return ResponseEntity conținand utilizatorul creat si HttpStatus.CREATED
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    /**
     * Actualizeaza un utilizator existent.
     *
     * @param id         ID-ul utilizatorului care urmeaza sa fie actualizat
     * @param newDetails Detaliile actualizate ale utilizatorului
     * @return ResponseEntity conținand utilizatorul gasit sau HttpStatus.NOT_FOUND daca utilizatorul nu exista
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
     * Sterge un utilizator dupa ID.
     *
     * @param id ID-ul utilizatorului care urmeaza sa fie sters
     * @return ResponseEntity cu HttpStatus.OK pentru confirmarea stergerii
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Verifica incercarea de login.
     * Daca exista utilizatorul cu username-ul respectiv si parola este la fel, atunci se poate autentifica
     *
     * @param user contine username-ul si parola pentru care doresc sa fac login
     * @return ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // Autentificare reușită, returnează utilizatorul
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            // Autentificare eșuată, returnează null
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

}
