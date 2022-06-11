package ru.itis.headhunter.services;

import ru.itis.headhunter.dto.CompanyDto;
import ru.itis.headhunter.dto.forms.CompanyForm;
import ru.itis.headhunter.dto.pages.CompaniesPageDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyDto createCompany(CompanyForm companyForm);

    CompaniesPageDto getAllCompanies(Long page, Optional<String> sortBy);
}
