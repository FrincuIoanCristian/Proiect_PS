package com.example.restservice.contract;

import com.example.restservice.contract.data.UserData;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Teste pentru clasa UserContract.
 */
public class UserContractTest {
    @Mock
    private UserRepository userRepositoryMock;
    private UserContract userContract;

    /**
     * Initializarea testelor.
     */
    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        userContract = new UserData(userRepositoryMock);
    }

    /**
     * Test pentru metoda findAll.
     */
    @Test
    public void findAllTest() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "username1", "password1", "email1", "fullName1", "avatar1", 100.0));
        users.add(new User(2, "username2", "password2", "email2", "fullName2", "avatar2", 200.0));
        when(userRepositoryMock.findAll()).thenReturn(users);
        List<User> result = userContract.findAll();
        verify(userRepositoryMock).findAll();
        assertEquals(users, result);
    }

    /**
     * Test pentru metoda findById, cand il gaseste
     */
    @Test
    public void findByIdFoundTest() {
        Long userId = 1L;
        User user = new User(userId, "username", "password", "email", "fullName", "avatar", 100.0);
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> result = userContract.findById(userId);
        verify(userRepositoryMock).findById(userId);
        assertEquals(Optional.of(user), result);
    }

    /**
     * Test pentru metoda findById, cand nu il gaseste.
     */
    @Test
    public void findByIdNotFoundTest() {
        Long userId = 1L;
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());
        Optional<User> result = userContract.findById(userId);
        verify(userRepositoryMock).findById(userId);
        assertEquals(Optional.empty(), result);
    }

    /**
     * Test pentru metoda save.
     */
    @Test
    public void saveTest() {
        User user = new User(1, "username", "password", "email", "fullName", "avatar", 100.0);
        when(userRepositoryMock.save(user)).thenReturn(user);
        User result = userContract.save(user);
        verify(userRepositoryMock).save(user);
        assertEquals(user, result);
    }

    /**
     * Test pentru metoda deleteById.
     */
    @Test
    public void deleteByIdTest() {
        Long userId = 1L;
        userContract.deleteById(userId);
        verify(userRepositoryMock).deleteById(userId);
    }

    /**
     * Test pentru metoda findByUsername, cand il gaseste
     */
    @Test
    public void findByUsernameFoundTest() {
        String username = "user1";
        User user = new User(1, username, "password", "email", "fullName", "avatar", 100.0);
        when(userRepositoryMock.findByUsername(username)).thenReturn(user);
        User result = userContract.findByUsername(username);
        verify(userRepositoryMock).findByUsername(username);
        assertEquals(user, result);
    }

    /**
     * Test pentru metoda findByUsername, cand nu il gaseste
     */
    @Test
    public void findByUsernameNotFoundTest() {
        String username = "user1";
        when(userRepositoryMock.findByUsername(username)).thenReturn(null);
        User result = userContract.findByUsername(username);
        verify(userRepositoryMock).findByUsername(username);
        assertNull(result);
    }


}
