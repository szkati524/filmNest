package com.filmNest.example.filmNest.model;

import com.filmNest.example.filmNest.Enums.MovieType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_hashtags",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<HashTag> hashTags = new HashSet<>();
    @Column(length = 2000)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String review;
    @Enumerated(EnumType.STRING)
     private MovieType movieType;

    public Movie(Long id, String name, String url, Set<HashTag> hashTags, String description, User user, String review,MovieType movieType) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hashTags = hashTags;
        this.description = description;
        this.user = user;
        this.review = review;
        this.movieType = movieType;
    }
    public Movie(){

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        if (hashTags==null){
            this.hashTags = new HashSet<>();
        } else {
            this.hashTags = hashTags;
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }


    public void setReview(String review) {
        this.review = review;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void addHashTag(HashTag tag) {
        this.hashTags.add(tag);
        tag.getMovies().add(this);
    }

}
