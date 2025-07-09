package com.filmNest.example.filmNest.repository;

import com.filmNest.example.filmNest.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
@Transactional
@Modifying
@Query("delete from User u where u.email = :email")
   public int deleteByEmail(String email);
   Optional<User> findByEmail(String email);
}
