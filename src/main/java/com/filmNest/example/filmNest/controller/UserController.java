package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.dto.UserDTO;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user){
        UserDTO createdUser = userService.createUser(user);

        createdUser.add(linkTo(methodOn(UserController.class).getUserById(createdUser.getId())).withSelfRel());
        createdUser.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(user -> {
                    user.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
                    user.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
                    return ResponseEntity.ok(user);
                } )
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        users.forEach(user -> {
            user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
        });
        return  ResponseEntity.ok(users);
    }
    @DeleteMapping("/by-email")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
