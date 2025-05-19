package com.filmNest.example.filmNest.service;


import com.filmNest.example.filmNest.dto.UserDTO;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("użytkownik nie może być null");
        }
        User saved = userRepository.save(user);
        return mapToDto(saved);
    }



    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDto);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }
@Transactional
    public void deleteUserByEmail(String email) {
        if (email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email musi być uzupełniony");
        }
        userRepository.deleteUserByEmail(email);

    }
    private UserDTO mapToDto(User user) {
        return new UserDTO(user.getId(), user.getUserName(),user.getEmail());
    }
}
