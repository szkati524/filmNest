package com.filmNest.example.filmNest.dto;

import com.filmNest.example.filmNest.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MovieDTO {
    private Long id;
    private String name;
    private String url;
    private String hashTag;
    private String description;
    private String userEmail;
    private String review;


    MovieDTO() {

    }

    public MovieDTO(Long id, String name, String url, String hashTag, String description, String userEmail, String review) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hashTag = hashTag;
        this.description = description;
        this.userEmail = userEmail;
        this.review = review;
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

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
