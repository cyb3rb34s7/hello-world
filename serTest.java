package com.example.movieapi.service.impl;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.mapper.MovieMapper;
import com.example.movieapi.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMovie() {
        Movie movie = new Movie();
        movieService.createMovie(movie);
        verify(movieMapper).insertMovie(movie);
    }

    @Test
    void getMovieById_Found() {
        Long id = 1L;
        Movie movie = new Movie();
        when(movieMapper.getMovieById(id)).thenReturn(movie);

        Movie result = movieService.getMovieById(id);

        assertEquals(movie, result);
    }

    @Test
    void getMovieById_NotFound() {
        Long id = 1L;
        when(movieMapper.getMovieById(id)).thenReturn(null);

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(id));
    }

    @Test
    void getMovies() {
        String sortCol = "name";
        String sortOrder = "ASC";
        Integer limit = 10;
        Integer offset = 0;

        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieMapper.getMovies(sortCol, sortOrder, limit, offset)).thenReturn(movies);

        List<Movie> result = movieService.getMovies(sortCol, sortOrder, limit, offset);

        assertEquals(movies, result);
    }

    @Test
    void getTotalMovieCount() {
        when(movieMapper.getTotalMovieCount()).thenReturn(5);

        int result = movieService.getTotalMovieCount();

        assertEquals(5, result);
    }
}
