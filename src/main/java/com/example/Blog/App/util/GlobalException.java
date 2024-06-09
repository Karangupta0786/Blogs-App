package com.example.Blog.App.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.field,
                exception.field_type,
                exception.value,
                String.format("%s is not available at %s: %d", exception.field, exception.field_type, exception.value)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

@Getter
@Setter
@AllArgsConstructor
class ErrorResponse {
    private int statusCode;
    private String field;
    private String field_type;
    private Long value;
    String message;
}