package com.lowkey.movieservice.service;

import com.lowkey.movieservice.model.Movie;
import com.lowkey.movieservice.model.MovieApiResponse;
import com.lowkey.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Retrieve all movies as API responses
    public List<MovieApiResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll(); // Fetch all movies from database
        return movies.stream()
                .map(movie -> new MovieApiResponse(
                        movie.getTitle(),
                        "A great movie", // Add actual description if available
                        "Drama",         // Replace with movie genre if available
                        2023             // Replace with release year if available
                ))
                .collect(Collectors.toList());
    }

    // Retrieve a specific movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id); // Fetch movie by ID
    }

    // Add a new movie to the database
    public Movie addMovie(Movie movie) {
        if (movieRepository.findByTitle(movie.getTitle()) != null) {
            throw new IllegalArgumentException("A movie with this title already exists.");
        }
        return movieRepository.save(movie); // Save the new movie
    }

    // Delete a movie by ID
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie with ID " + id + " does not exist.");
        }
        movieRepository.deleteById(id); // Delete the movie
    }
}