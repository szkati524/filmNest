package com.filmNest.example.filmNest.service;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.model.Movie;
import com.filmNest.example.filmNest.model.MovieBuilder;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.MovieRepository;
import com.filmNest.example.filmNest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public MovieDTO addMovie(MovieDTO movieDTO){
        User user = userRepository.findByEmail(movieDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

                Movie movie = new MovieBuilder()
                        .setName(movieDTO.getName())
                        .setUrl(movieDTO.getUrl())
                        .setHashTag(movieDTO.getHashTag())
                        .setDescription(movieDTO.getDescription())
                        .setReview(movieDTO.getReview())
                        .setUser(user)
                        .createMovie();
        Movie savedMovie = movieRepository.save(movie);
        return mapToDTO(savedMovie);


    }
    public List<MovieDTO> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    private MovieDTO mapToDTO(Movie movie) {
        return new MovieDTO(
        movie.getId(),
        movie.getName(),
        movie.getUrl(),
                movie.getHashTag(),
        movie.getDescription(),
                movie.getUser().getEmail(),
        movie.getReview()
        );
    }
}
