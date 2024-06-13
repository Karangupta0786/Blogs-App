package com.example.Blog.App.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleException(CustomException exception) {
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodException(MethodArgumentNotValidException exception){
        Map<String,String> result = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(e->{
            String message = e.getDefaultMessage();
            String fields = ((FieldError)e).getField();
            result.put(fields,message);
                }
                );
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}