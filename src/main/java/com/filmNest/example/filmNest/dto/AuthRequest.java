package com.filmNest.example.filmNest.dto;

import org.springframework.security.authentication.AuthenticationManager;

public class AuthRequest {

    public AuthRequest(){

    }
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
