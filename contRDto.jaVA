package com.example.app.movieManagement.controller;

import com.example.app.movieManagement.dto.ApiResponse;
import com.example.app.movieManagement.model.Movie;
import com.example.app.movieManagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ApiResponse<Movie>> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        ApiResponse<Movie> response = ApiResponse.<Movie>builder()
                .status(ApiResponse.ResponseStatus.SUCCESS)
                .payload(createdMovie)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        ApiResponse<Movie> response = ApiResponse.<Movie>builder()
                .status(ApiResponse.ResponseStatus.SUCCESS)
                .payload(movie)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMovies(
            @RequestParam(defaultValue = "name") String sortCol,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {

        List<Movie> movies = movieService.getMovies(sortCol, sortOrder, limit, offset);
        int totalCount = movieService.getTotalMovieCount();

        Map<String, Object> payload = Map.of(
                "movies", movies,
                "totalCount", totalCount
        );

        ApiResponse<Map<String, Object>> response = ApiResponse.<Map<String, Object>>builder()
                .status(ApiResponse.ResponseStatus.SUCCESS)
                .payload(payload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
