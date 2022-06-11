package ru.itis.headhunter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.api.VacancyApi;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.VacancyDto;
import ru.itis.headhunter.dto.VacancyResponseDto;
import ru.itis.headhunter.dto.forms.VacancyForm;
import ru.itis.headhunter.dto.pages.VacanciesPageDto;
import ru.itis.headhunter.models.VacancyResponse;
import ru.itis.headhunter.services.VacancyService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VacancyController implements VacancyApi {
    private final VacancyService vacancyService;

    @Override
    public ResponseEntity<AccountDto> acceptResponse(Long vacancyResponseId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.acceptVacancyResponse(vacancyResponseId));
    }

    @Override
    public ResponseEntity<VacancyDto> addToFavorites(Long vacancyId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.addVacancyToFavorites(vacancyId));
    }

    @Override
    public ResponseEntity<VacancyDto> createVacancy(VacancyForm body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.addVacancy(body));
    }

    @Override
    public ResponseEntity<VacanciesPageDto> getAllVacancy(Long page, Optional<String> sortBy,
                                                          Optional<Long> salaryFrom, Optional<Long> salaryTo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.getSortVacancies(page, sortBy, salaryFrom, salaryTo));
    }

    @Override
    public ResponseEntity<VacanciesPageDto> getByCompany(Long companyId, Long page, Optional<String> sortBy) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.getAllByCompany(companyId, page, sortBy));
    }

    @Override
    public ResponseEntity<VacancyResponseDto> respondToVacancy(Long vacancyId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.respondToVacancy(vacancyId));
    }

    @Override
    public ResponseEntity<VacanciesPageDto> getBySpecialization(String specialization, Long page, Optional<String> sortBy) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vacancyService.getAllBySpecialization(specialization, page, sortBy));
    }
}
