package com.filmNest.example.filmNest.model;

public class MovieBuilder {
    private Long id;
    private String name;
    private String url;
    private String hashTag;
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

    public MovieBuilder setHashTag(String hashTag) {
        this.hashTag = hashTag;
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
        return new Movie(id, name, url, hashTag, description, user, review);
    }
}