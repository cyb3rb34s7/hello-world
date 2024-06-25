package com.example.movieapi.mapper;

import com.example.movieapi.model.Movie;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    void insertMovie() {
        Movie movie = new Movie();
        movie.setName("Test Movie");
        movieMapper.insertMovie(movie);

        Movie insertedMovie = movieMapper.getMovieById(movie.getId());
        assertNotNull(insertedMovie);
        assertEquals("Test Movie", insertedMovie.getName());
    }

    @Test
    void getMovieById() {
        Movie movie = new Movie();
        movie.setName("Test Movie");
        movieMapper.insertMovie(movie);

        Movie result = movieMapper.getMovieById(movie.getId());
        assertNotNull(result);
        assertEquals("Test Movie", result.getName());
    }

    @Test
    void getMovies() {
        Movie movie1 = new Movie();
        movie1.setName("Test Movie 1");
        Movie movie2 = new Movie();
        movie2.setName("Test Movie 2");
        movieMapper.insertMovie(movie1);
        movieMapper.insertMovie(movie2);

        List<Movie> result = movieMapper.getMovies("name", "ASC", 10, 0);
        assertFalse(result.isEmpty());
        assertTrue(result.size() >= 2);
    }

    @Test
    void getTotalMovieCount() {
        int initialCount = movieMapper.getTotalMovieCount();

        Movie movie = new Movie();
        movie.setName("Test Movie");
        movieMapper.insertMovie(movie);

        int newCount = movieMapper.getTotalMovieCount();
        assertEquals(initialCount + 1, newCount);
    }
}
