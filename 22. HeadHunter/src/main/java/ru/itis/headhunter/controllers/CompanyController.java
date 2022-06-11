package ru.itis.headhunter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.CompanyApi;
import ru.itis.headhunter.dto.CompanyDto;
import ru.itis.headhunter.dto.forms.CompanyForm;
import ru.itis.headhunter.dto.pages.CompaniesPageDto;
import ru.itis.headhunter.services.CompanyService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CompanyController implements CompanyApi {
    private final CompanyService companyService;

    @Override
    public ResponseEntity<CompaniesPageDto> getCompanies(Long page, Optional<String> sortBy) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getAllCompanies(page, sortBy));
    }

    @Override
    public ResponseEntity<CompanyDto> createCompany(CompanyForm body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.createCompany(body));
    }
}
