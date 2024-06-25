package com.example.app.movieManagement.exception;

import com.example.app.movieManagement.dto.ApiResponse;
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
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleMovieNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ApiResponse.ResponseStatus.FAIL, response.getBody().getStatus());
        assertEquals("MOVIE_NOT_FOUND", response.getBody().getError().getCode());
        assertEquals("Movie not found", response.getBody().getError().getMessage());
    }

    @Test
    void handleGeneralException() {
        Exception ex = new Exception("General error");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleGeneralException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ApiResponse.ResponseStatus.FAIL, response.getBody().getStatus());
        assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getError().getCode());
        assertEquals("An unexpected error occurred", response.getBody().getError().getMessage());
    }
}
