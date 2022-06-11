package ru.itis.headhunter.services;

import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.VacancyDto;
import ru.itis.headhunter.dto.VacancyResponseDto;
import ru.itis.headhunter.dto.forms.VacancyForm;
import ru.itis.headhunter.dto.pages.VacanciesPageDto;

import java.util.List;
import java.util.Optional;

public interface VacancyService {
    VacancyDto addVacancy(VacancyForm vacancyForm);
    VacanciesPageDto getSortVacancies(Long page, Optional<String> sortBy, Optional<Long> salaryFrom, Optional<Long> salaryTo);
    VacanciesPageDto getAllByCompany(Long companyId, Long page, Optional<String> sortBy);

    VacancyResponseDto respondToVacancy(Long vacancyId);

    AccountDto acceptVacancyResponse(Long vacancyResponseId);

    VacancyDto addVacancyToFavorites(Long vacancyId);

    VacanciesPageDto getAllBySpecialization(String specialization, Long page, Optional<String> sortBy);
}
