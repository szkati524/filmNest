package com.filmNest.example.filmNest.model;

import java.util.Set;

public class MovieBuilder {
    private Long id;
    private String name;
    private String url;
    private Set<HashTag> hashTags;
    private String description;
    private User user;
    private String review;

    public MovieBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MovieBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MovieBuilder setUrl(String url) {
        this.url = url;
        return this;
    }


    public MovieBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public MovieBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public MovieBuilder setReview(String review) {
        this.review = review;
        return this;
    }

    public Movie createMovie() {
       Movie movie = new Movie(id,name,url,hashTags,description,user,review);
       movie.setHashTags(this.hashTags);
       return movie;
    }

    public MovieBuilder setHashTags(Set<HashTag> hashtags) {
        this.hashTags = hashTags;
        return this;
    }
}