package com.example.movieapi.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleMovieNotFoundException() {
        MovieNotFoundException ex = new MovieNotFoundException("Movie not found");
        ResponseEntity<String> response = exceptionHandler.handleMovieNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Movie not found", response.getBody());
    }

    @Test
    void handleGeneralException() {
        Exception ex = new Exception("General error");
        ResponseEntity<String> response = exceptionHandler.handleGeneralException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: General error", response.getBody());
    }
}
