package com.example.app.movieManagement.exception;

import com.example.app.movieManagement.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMovieNotFoundException(MovieNotFoundException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(ApiResponse.ResponseStatus.FAIL)
                .error(ApiResponse.ErrorDetails.builder()
                        .code("MOVIE_NOT_FOUND")
                        .message(ex.getMessage())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(ApiResponse.ResponseStatus.FAIL)
                .error(ApiResponse.ErrorDetails.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .message("An unexpected error occurred")
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
