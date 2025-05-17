package com.filmNest.example.filmNest.service;


import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User user;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setUserName("test");
    }
    @Test
    public void createUserTest(){
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.createUser(user);

        verify(userRepository,times(1)).save(user);
       assertNotNull(savedUser);
       assertEquals("test@example.com",savedUser.getEmail());
    }
    @Test
    public void getUserByIdExistUserTest(){
when(userRepository.findById(1L)).thenReturn(Optional.of(user));

Optional<User> result = userService.getUserById(1L);


assertTrue(result.isPresent());
assertEquals(user.getEmail(),result.get().getEmail());
verify(userRepository,times(1)).findById(1L);


    }
    @Test
    public void getUserByIdIsNotExistReturnEmptyOptional(){
       Long userId = 99L;
       when(userRepository.findById(userId)).thenReturn(Optional.empty());
       Optional<User> result = userService.getUserById(userId);
       verify(userRepository,times(1)).findById(userId);
       assertFalse(result.isPresent());
    }
    @Test
    public void getAllUsersTest(){
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        verify(userRepository,times(1)).findAll();
        assertEquals(users,result);
    }
}
