package com.lowkey.movieservice.model;

public class MovieApiResponse {

    private String title;
    private String description;
    private String genre;
    private int releaseYear;

    // Constructors
    public MovieApiResponse() {}

    public MovieApiResponse(String title, String description, String genre, int releaseYear) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}