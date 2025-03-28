package com.lowkey.movieservice.auth;

import com.lowkey.movieservice.model.Movie;
import com.lowkey.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public UserDetails loadUserByUsername(String title) throws UsernameNotFoundException {
        Movie movie = movieRepository.findByTitle(title); // Ensure findByTitle exists in MovieRepository

        if (movie == null) {
            throw new UsernameNotFoundException("Movie not found with title: " + title);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(movie.getTitle()) // Ensure getTitle exists in Movie
                .password(movie.getPassword()) // Ensure getPassword exists in Movie
                .roles("USER") // Default role
                .build();
    }
}