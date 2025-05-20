package com.filmNest.example.filmNest.service;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.model.HashTag;
import com.filmNest.example.filmNest.model.Movie;
import com.filmNest.example.filmNest.model.MovieBuilder;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.HashTagRepository;
import com.filmNest.example.filmNest.repository.MovieRepository;
import com.filmNest.example.filmNest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository, UserRepository userRepository, HashTagRepository hashTagRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.hashTagRepository = hashTagRepository;
    }

    public MovieDTO addMovie(MovieDTO movieDTO){
        User user = userRepository.findByEmail(movieDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

        Set<HashTag> hashtags = movieDTO.getHashTag().stream()
                .map(tag -> hashTagRepository.findByName(tag)
                        .orElseGet(() -> new HashTag(tag)))
                .collect(Collectors.toSet());


                Movie movie = new MovieBuilder()
                        .setName(movieDTO.getUserName())
                        .setUrl(movieDTO.getUrl())
                        .setHashTags(hashtags)
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
    public MovieDTO getMovieById(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film nie został znaleziony"));
        return mapToDTO(movie);
    }
    private MovieDTO mapToDTO(Movie movie) {
        List<String> hashtags = new ArrayList<>();
        if (movie.getHashTags() != null){
            hashtags = movie.getHashTags().stream()
                    .map(HashTag::getName)
                    .collect(Collectors.toList());
        }

        return new MovieDTO(
        movie.getId(),
        movie.getName(),
        movie.getUrl(),
                hashtags,
        movie.getDescription(),
                movie.getUser().getEmail(),
        movie.getReview()
        );
    }
    public List<Movie> findMoviesByHashTag(String name){
        return movieRepository.findByHashTags_Name(name);
    }

}
