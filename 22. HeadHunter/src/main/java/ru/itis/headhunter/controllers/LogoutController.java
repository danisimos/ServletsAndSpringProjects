package ru.itis.headhunter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.LogoutApi;

@RestController
public class LogoutController implements LogoutApi {
    @Override
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Success logout");
    }
}
