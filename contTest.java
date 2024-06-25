package com.example.movieapi.controller;

import com.example.movieapi.model.Movie;
import com.example.movieapi.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMovie() {
        Movie movie = new Movie();
        movie.setName("Test Movie");

        ResponseEntity<Movie> response = movieController.createMovie(movie);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movie, response.getBody());
        verify(movieService).createMovie(movie);
    }

    @Test
    void getMovieById_Found() {
        Long id = 1L;
        Movie movie = new Movie();
        movie.setId(id);
        when(movieService.getMovieById(id)).thenReturn(movie);

        ResponseEntity<Movie> response = movieController.getMovieById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    void getMovieById_NotFound() {
        Long id = 1L;
        when(movieService.getMovieById(id)).thenReturn(null);

        ResponseEntity<Movie> response = movieController.getMovieById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getMovies() {
        String sortCol = "name";
        String sortOrder = "ASC";
        Integer limit = 10;
        Integer offset = 0;

        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getMovies(sortCol, sortOrder, limit, offset)).thenReturn(movies);
        when(movieService.getTotalMovieCount()).thenReturn(2);

        ResponseEntity<Map<String, Object>> response = movieController.getMovies(sortCol, sortOrder, limit, offset);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(movies, response.getBody().get("movies"));
        assertEquals(2, response.getBody().get("totalCount"));
    }
}
