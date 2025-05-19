package com.filmNest.example.filmNest.service;

import com.filmNest.example.filmNest.dto.MovieDTO;
import com.filmNest.example.filmNest.model.Movie;
import com.filmNest.example.filmNest.model.MovieBuilder;
import com.filmNest.example.filmNest.model.User;
import com.filmNest.example.filmNest.repository.MovieRepository;
import com.filmNest.example.filmNest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MovieService movieService;


    private MovieDTO movieDTO;
    private User user;
    private Movie movie;

    @BeforeEach
    void setUp(){
        movieDTO = new MovieDTO(
                null,
                "Inception",
                "http://trailer.com/inception",
                "#sci-fi",
                "A mind-bending movie",
                "test@example.com",
                "Amazing!"
        );

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        movie = new MovieBuilder()
                .setName(movieDTO.getUserName())
                .setUrl(movieDTO.getUrl())
                .setHashTag(movieDTO.getHashTag())
                .setDescription(movieDTO.getDescription())
                .setReview(movieDTO.getReview())
                .setUser(user)
                .createMovie();
        movie.setId(1L);

    }
    @Test
    public void addMovieShouldSaveAndReturnDto(){
        when(userRepository.findByEmail(movieDTO.getUserEmail())).thenReturn(Optional.of(user));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        MovieDTO result = movieService.addMovie(movieDTO);
        assertNotNull(result);
        assertEquals("Inception",result.getUserName());
        assertEquals("test@example.com",result.getUserEmail());
        verify(userRepository,times(1)).findByEmail("test@example.com");
        verify(movieRepository,times(1)).save(any(Movie.class));
    }
    @Test
    public void addMovieShouldThrowExceptionWhenUserNotFound(){
        movieDTO.setUserEmail("notfound@example.com");
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            movieService.addMovie(movieDTO);
        });
        verify(userRepository,times(1)).findByEmail("notfound@example.com");
        verify(movieRepository,never()).save(any(Movie.class));
    }
    @Test
    public void getAllMoviesReturnListOfMovieDtos(){
        movie.setId(1L);
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<MovieDTO> result = movieService.getAllMovies();
        assertEquals(1,result.size());
        assertEquals("Inception",result.get(0).getUserName());
        assertEquals("test@example.com",result.get(0).getUserEmail());
        verify(movieRepository,times(1)).findAll();
    }
    @Test
    public void getAllMoviesShouldReturnEmptyListWhenNoMovies(){
        when(movieRepository.findAll()).thenReturn(Collections.emptyList());
        List<MovieDTO> result = movieService.getAllMovies();
        assertTrue(result.isEmpty());
        verify(movieRepository,times(1)).findAll();
    }
}
