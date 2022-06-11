package ru.itis.headhunter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.CvApi;
import ru.itis.headhunter.dto.CVDto;
import ru.itis.headhunter.dto.forms.CVForm;
import ru.itis.headhunter.dto.pages.CVPageDto;
import ru.itis.headhunter.services.CVService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CVController implements CvApi {
    private final CVService cvService;

    @Override
    public ResponseEntity<CVDto> createCV(CVForm body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cvService.createCV(body));
    }

    @Override
    public ResponseEntity<CVPageDto> getAllCV(Long page, Optional<String> sortBy) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cvService.getAllCV(page, sortBy));
    }
}
