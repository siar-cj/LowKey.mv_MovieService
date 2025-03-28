package com.lowkey.movieservice.repository;

import com.lowkey.movieservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title); // Ensure this is correctly defined
}