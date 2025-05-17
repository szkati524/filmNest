package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.model.Movie;
import com.filmNest.example.filmNest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
@Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO){
    MovieDTO savedMovieDTO = movieService.addMovie(movieDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieDTO);



    }
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
    List<MovieDTO> movies = movieService.getAllMovies();
    return ResponseEntity.ok(movies);
    }
}
