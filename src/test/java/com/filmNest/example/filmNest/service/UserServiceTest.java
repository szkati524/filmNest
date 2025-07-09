package com.filmNest.example.filmNest.service;


import com.filmNest.example.filmNest.dto.UserDTO;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
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
    public void createUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO savedUser = userService.createUser(user);

        verify(userRepository, times(1)).save(user);
        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("test", savedUser.getUserName());
    }

    @Test
    public void getUserByIdExistUserTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserDTO> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
        assertEquals(user.getUserName(), result.get().getUserName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void getUserByIdIsNotExistReturnEmptyOptional() {
        Long userId = 99L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserDTO> result = userService.getUserById(userId);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(user.getEmail(), result.get(0).getEmail());
    }

    @Test
    public void getAllUsersOnlyOneTest() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmail());
    }

    @Test
    public void deleteUserByEmailIsNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserByEmail(null));
        verify(userRepository, never()).deleteByEmail(any());
    }

    @Test
    public void deleteUserByEmailIsEmptyShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserByEmail(""));
        verify(userRepository, never()).deleteByEmail(any());
    }

    @Test
    public void createUserServiceDoesntChangeFieldsTest() {
        User newUser = new User();
        newUser.setUserName("jan");
        newUser.setEmail("test@example.com");
        newUser.setId(1L);

        when(userRepository.save(newUser)).thenReturn(newUser);

        UserDTO result = userService.createUser(newUser);

        assertEquals("jan", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void getUserByBigIdShouldReturnEmptyTest() {
        long userId = 13262326L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserDTO> result = userService.getUserById(userId);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void getAllUsersListShouldBeEmptyTest() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserDTO> result = userService.getAllUsers();

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void deleteUserByCorrectEmailTest() {
        String userEmail = "delete@example.com";
        doNothing().when(userRepository).deleteByEmail(userEmail);

        userService.deleteUserByEmail(userEmail);

        verify(userRepository, times(1)).deleteByEmail(userEmail);
    }
}

