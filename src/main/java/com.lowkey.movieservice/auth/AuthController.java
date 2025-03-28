package com.lowkey.movieservice.auth;

import com.lowkey.movieservice.model.Movie;
import com.lowkey.movieservice.model.ConfirmationToken;
import com.lowkey.movieservice.repository.MovieRepository;
import com.lowkey.movieservice.repository.ConfirmationTokenRepository;
import com.lowkey.movieservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String registerMovie(@RequestBody Movie movie) {
        if (movieRepository.findByTitle(movie.getTitle()) != null) {
            throw new IllegalArgumentException("Movie is already registered.");
        }

        movieRepository.save(movie);

        ConfirmationToken token = new ConfirmationToken(
                UUID.randomUUID().toString(),
                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24), // 24 hours validity
                movie
        );
        tokenRepository.save(token);

        emailService.sendMovieConfirmationEmail(movie.getEmail(), token.getToken());

        return "Registration successful. Please check your email to confirm.";
    }

    @GetMapping("/confirm")
    public String confirmMovie(@RequestParam String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);
        if (confirmationToken != null && confirmationToken.getExpiryDate().after(new Date())) {
            Movie movie = confirmationToken.getMovie();
            movie.setConfirmed(true);
            movieRepository.save(movie);
            return "Movie confirmed successfully!";
        }
        return "Invalid or expired confirmation link.";
    }
}