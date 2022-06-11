package ru.itis.blogrestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.blogrestapi.validation.http.ValidationExceptionDto;
import ru.itis.blogrestapi.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler
    public ResponseEntity<ValidationExceptionResponse> handle(MethodArgumentNotValidException exception) {
        List<ValidationExceptionDto> exceptions = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            ValidationExceptionDto validationExceptionDto = ValidationExceptionDto.builder()
                    .object(objectError.getObjectName())
                    .message(objectError.getDefaultMessage())
                    .build();

            exceptions.add(validationExceptionDto);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationExceptionResponse(exceptions));
    }
}
