package com.filmNest.example.filmNest.dto;

import com.filmNest.example.filmNest.model.HashTag;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class MovieDTO extends RepresentationModel<MovieDTO> {
    private Long id;
    private String userName;
    private String url;
    private List<String> hashTags;
    private String description;
    private String userEmail;
    private String review;

    public MovieDTO(Long id, String userName, String url, List<String> hashTags, String description, String userEmail, String review) {
        this.id = id;
        this.userName = userName;
        this.url = url;
        this.hashTags = hashTags;
        this.description = description;
        this.userEmail = userEmail;
        this.review = review;
    }

    MovieDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getHashTag() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
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

