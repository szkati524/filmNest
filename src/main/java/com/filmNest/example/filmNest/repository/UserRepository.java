package com.filmNest.example.filmNest.repository;

import com.filmNest.example.filmNest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   public void deleteUserByEmail(String email);
   Optional<User> findByEmail(String email);
}
