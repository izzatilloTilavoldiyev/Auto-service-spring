package com.company.autoservice.handlers;

import com.company.autoservice.dtos.response.AppErrorDTO;
import com.company.autoservice.exception.DuplicateValueException;
import com.company.autoservice.exception.ItemNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<AppErrorDTO> dataNotFoundExceptionHandler(RuntimeException e, HttpServletRequest request) {
        AppErrorDTO errorDTO = new AppErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                404
        );
        return ResponseEntity.status(404).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = "Input is not valid";
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<AppErrorDTO> httpMessageNotReadableExceptionHandler(RuntimeException e, HttpServletRequest request) {
        AppErrorDTO errorDTO = new AppErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                400
        );
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler({DuplicateValueException.class})
    public ResponseEntity<AppErrorDTO> duplicateValueExceptionHandler(RuntimeException e, HttpServletRequest request) {
        AppErrorDTO errorDTO = new AppErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                409
        );
        return ResponseEntity.status(409).body(errorDTO);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppErrorDTO> expiredJwtExceptionHandler(ExpiredJwtException e, HttpServletRequest request) {
        return ResponseEntity.status(401)
                .body(new AppErrorDTO(request.getRequestURI(), "Token has expired .", 401));
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<AppErrorDTO> fileNotFoundExceptionHandler(FileNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }
}
