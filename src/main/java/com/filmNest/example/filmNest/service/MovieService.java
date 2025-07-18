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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    public MovieDTO addMovie(MovieDTO movieDTO) {
        User user = userRepository.findByEmail(movieDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

        Set<HashTag> hashtags = movieDTO.getHashTags().stream()
                .map(tag -> hashTagRepository.findByName(tag)
                        .orElseGet(() -> hashTagRepository.save(new HashTag(tag))))

                .collect(Collectors.toSet());



        Movie movie = new MovieBuilder()
                .setName(movieDTO.getName())
                .setUrl(movieDTO.getUrl())
                .setDescription(movieDTO.getDescription())
                .setReview(movieDTO.getReview())
                .setUser(user)
                .setMovieType(movieDTO.getMovieType())
                .createMovie();

        for (HashTag tag : hashtags) {
            movie.addHashTag(tag);
        }

        hashtags.forEach(hashTag -> hashTag.getMovies().add(movie));
        Movie savedMovie = movieRepository.save(movie);
        return mapToDTO(savedMovie);


    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public MovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film nie został znaleziony"));
        return mapToDTO(movie);
    }

    private MovieDTO mapToDTO(Movie movie) {
        List<String> hashtags = new ArrayList<>();
        if (movie.getHashTags() != null) {
            hashtags = movie.getHashTags().stream()
                    .map(HashTag::getName)
                    .collect(Collectors.toList());
        }

        return new MovieDTO(
                movie.getId(),
                movie.getName(),
                movie.getUser().getUserName(),
                movie.getUrl(),
                hashtags,
                movie.getDescription(),
                movie.getUser().getEmail(),
                movie.getReview(),
                movie.getMovieType()
        );
    }

public List<MovieDTO> findMoviesByHashTags(List<String> tags){
        List<Movie> movies = movieRepository.findDistinctByHashTags_NameIn((tags));
        return movies.stream()
                .map(this::mapToDTO)
                .toList();

}
public List<MovieDTO> findMovieByName(String name){
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(name);
        return movies.stream()
                .map(this::mapToDTO)
                .toList();
}
public void deleteMovieById(Long id){
        if (!movieRepository.existsById(id)){
            throw new RuntimeException("Film o id " + id + " nie istnieje");
        }
        movieRepository.deleteById(id);
}

}
