package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Obtine un utilizator dupa un ID.
     * Se verifica cu URL-ul 'http://localhost:8080/users/get/{id}', iar in loc de {id} se pune ID-ul urilizatorului dorit.
     * @param id    Id-ul utilizatorului cautat.
     * @return  Un obiect ResponseEntity care contine utilizatorul si mesajul OK, sau un mesaj de eroare in caz ca nu exista.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        if(user != null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creaza un utilizator nou.
     * Se verifica cu URL-ul 'http://localhost:8080/users/post'.
     * @param user  Detaliile noului utilizator.
     * @return  Un obiect ResponseEntity care contine utilizatorul creat si statusul HTTP corespunzator. Detaliile vor fi scrise in Body.
     */
    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser,HttpStatus.CREATED);
    }

    /**
     * Actualizeaza un utilizator existent.
     * Se verifica cu URL-ul 'http://localhost:8080/users/update/{id}', iar in loc de {id} se pune ID-ul urilizatorului dorit. Detaliile vor fi scrise in Body.
     * @param id    Id-ul utilizatorului care trebuie actualizat.
     * @param newDetails    Detaliile de actualizare a utilizatorului.
     * @return  Un obiect ResponseEntity care contine utilizatorul si mesajul OK, sau un mesaj de eroare in caz ca nu exista.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User newDetails){
        User updateUser = userService.updateUser(id,newDetails);
        if(updateUser != null){
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sterge un utilizator existent dupa un anumit ID.
     * Se verifica cu URL-ul 'http://localhost:8080/users/delete/{id}', iar in loc de {id} se pune ID-ul urilizatorului dorit.
     * @param id    ID-ul utilizatoruluo care trebuie sters.
     * @return Un obiect ResponseEntity care contine un mesaj de confirmare si statusul HTTP corespunzator.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
