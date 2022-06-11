package ru.itis.headhunter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.SignUpApi;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.forms.SignUpForm;
import ru.itis.headhunter.services.SignUpService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {
    private final SignUpService signUpService;

    @Override
    public ResponseEntity<AccountDto> signUp(SignUpForm body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signUpService.signUp(body));
    }
}

