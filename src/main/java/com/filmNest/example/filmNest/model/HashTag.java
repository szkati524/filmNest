package com.filmNest.example.filmNest.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(unique = true)
    private String name;
@ManyToMany(mappedBy = "hashtags")
private Set<Movie> movies = new HashSet<>();

public HashTag(){

}

    public HashTag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
