package com.filmNest.example.filmNest.dto;

import org.springframework.hateoas.RepresentationModel;

public class MovieDTO extends RepresentationModel<MovieDTO> {
    private Long id;
    private String userName;
    private String url;
    private String hashTag;
    private String description;
    private String userEmail;
    private String review;


    MovieDTO() {

    }

    public MovieDTO(Long id, String userName, String url, String hashTag, String description, String userEmail, String review) {
        this.id = id;
        this.userName = userName;
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
