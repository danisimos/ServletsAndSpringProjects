package ru.itis.headhunter.controllers.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.headhunter.exceptions.ConfirmCodeExpiredException;
import ru.itis.headhunter.exceptions.EmailSendingException;
import ru.itis.headhunter.exceptions.NotCompanyAccountException;
import ru.itis.headhunter.exceptions.NotFoundException;
import ru.itis.headhunter.exceptions.http.ExceptionDto;
import ru.itis.headhunter.exceptions.http.ValidationExceptionDto;
import ru.itis.headhunter.exceptions.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ValidationExceptionResponse> handleException(MethodArgumentNotValidException exception){
        List<ValidationExceptionDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            ValidationExceptionDto errorDto = ValidationExceptionDto.builder()
                    .message(errorMessage)
                    .object(error.getObjectName())
                    .build();

            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationExceptionResponse.builder()
                .exceptions(errors)
                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ValidationExceptionDto> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ValidationExceptionDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ValidationExceptionDto> handleNotCompanyAccountException(NotCompanyAccountException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ValidationExceptionDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleConfirmCodeExpiredException(ConfirmCodeExpiredException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleEmailSendingException(EmailSendingException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder().message(exception.getMessage()).build());
    }
}
