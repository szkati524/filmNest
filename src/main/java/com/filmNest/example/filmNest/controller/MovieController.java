package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.model.Movie;
import com.filmNest.example.filmNest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    savedMovieDTO.add(linkTo(methodOn(MovieController.class).getMovieById(savedMovieDTO.getId())).withSelfRel());
    savedMovieDTO.add(linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies"));
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieDTO);



    }
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies(){

    List<MovieDTO> movies = movieService.getAllMovies();
    movies.forEach(movie -> {
         movie.add(linkTo(methodOn(MovieController.class).getMovieById(movie.getId())).withSelfRel());
    });
    return ResponseEntity.ok(movies);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){
    MovieDTO movieDTO = movieService.getMovieById(id);
    movieDTO.add(linkTo(methodOn(MovieController.class).getMovieById(id)).withSelfRel());
    movieDTO.add(linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies"));
    return ResponseEntity.ok(movieDTO);
    }
    @GetMapping("/search")
    public List<MovieDTO> searchMoviesByHashTags(@RequestParam List<String> tags){
    return movieService.findMoviesByHashTags(tags);
    }
    @GetMapping("/searchByName")
    public List<MovieDTO> searchMoviesByName(@RequestParam String name){
    return movieService.findMovieByName(name);
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id){
    movieService.deleteMovieById(id);
    return ResponseEntity.noContent().build();
}
}
