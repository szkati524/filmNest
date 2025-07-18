package com.filmNest.example.filmNest.controller;

import com.filmNest.example.filmNest.Enums.MovieType;
import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final MovieService movieService;

    public MainController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "main";
    }

    @GetMapping("/films")
    public String getAllMoviesPage(Model model) {
        List<MovieDTO> movies = movieService.getAllMovies()
                .stream()
                .filter(movie -> movie.getMovieType() == MovieType.FILM)
                .sorted(Comparator.comparing(MovieDTO::getName))
                .toList();
        model.addAttribute("movies", movies);
        return "films";
    }

    @GetMapping("/series")
    public String showSeries(Model model) {
        List<MovieDTO> seriesOnly = movieService.getAllMovies().stream()
                .filter(movie -> movie.getMovieType() == MovieType.SERIAL)
                .toList();
        model.addAttribute("seriesList", seriesOnly);
        return "series";
    }

    @GetMapping("/searchByTagsPage")
    public String searchByTagsPage(@RequestParam(required = false) String tags, Model model) {
        if (tags != null && !tags.isEmpty()) {
            List<String> tagList = Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            List<MovieDTO> movies = movieService.findMoviesByHashTags(tagList);
            model.addAttribute("movies", movies);
        }
            return "searchByTagsPage";
        }
        @GetMapping("contact")
        public String contactPage(Model model){
        return "contact";
        }
    }


