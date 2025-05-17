package com.filmNest.example.filmNest.repository;

import com.filmNest.example.filmNest.model.Movie;

import com.filmNest.example.filmNest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
List<Movie> findAllByUser(User user);
}
