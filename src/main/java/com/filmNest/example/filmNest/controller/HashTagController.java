package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hashtags")
public class HashTagController {
private final MovieService movieService;
    @Autowired
    public HashTagController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{names}/movies")
    public ResponseEntity<List<MovieDTO>> getMoviesByHashTags(@PathVariable String name){
        List<MovieDTO> movies = movieService.getAllMovies().stream()
                .map(movie -> {
                    return movie;
                })
        .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

}
