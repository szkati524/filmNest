package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {

    private final MovieService movieService;

    public MainController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("movies",movieService.getAllMovies());
        return "main";
    }
    @GetMapping("/films")
    public String getAllMoviesPage(Model model){
        List<MovieDTO> movies = movieService.getAllMovies()
                .stream()
                .sorted(Comparator.comparing(MovieDTO::getName))
                .toList();
        model.addAttribute("movies",movies);
        return "films";
    }
}
