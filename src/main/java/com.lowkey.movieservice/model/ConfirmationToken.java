package com.lowkey.movieservice.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // Constructors
    public ConfirmationToken() {}

    public ConfirmationToken(String token, Date expiryDate, Movie movie) {
        this.token = token;
        this.expiryDate = expiryDate;
        this.movie = movie;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}