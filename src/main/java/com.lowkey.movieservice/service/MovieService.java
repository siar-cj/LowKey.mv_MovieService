package com.lowkey.movieservice.service;

import com.lowkey.movieservice.model.Movie;
import com.lowkey.movieservice.model.MovieApiResponse;
import com.lowkey.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieApiResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll(); // Ensure `findAll` exists in MovieRepository
        return movies.stream()
                .map(movie -> new MovieApiResponse(
                        movie.getTitle(),
                        "A great movie", // You can add actual description logic
                        "Drama", // Replace with actual genre if available
                        2023 // Replace with actual release year if available
                ))
                .collect(Collectors.toList());
    }
}