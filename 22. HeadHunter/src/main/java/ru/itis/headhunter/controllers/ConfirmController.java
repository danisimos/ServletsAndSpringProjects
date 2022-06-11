package ru.itis.headhunter.controllers;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.ConfirmAccountApi;
import ru.itis.api.SignUpApi;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.services.EmailService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ConfirmController implements ConfirmAccountApi {
    private final EmailService emailService;

    @Override
    public ResponseEntity<AccountDto> confirmAccount(String confirmCode) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(emailService.confirm(confirmCode));
    }

    @Override
    public ResponseEntity<AccountDto> sendAnotherConfirmCode(String confirmCode) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(emailService.sendAnotherConfirmCode(confirmCode));
    }
}

