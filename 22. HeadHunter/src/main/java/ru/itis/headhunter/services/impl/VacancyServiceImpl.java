package ru.itis.headhunter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.VacancyDto;
import ru.itis.headhunter.dto.VacancyResponseDto;
import ru.itis.headhunter.dto.forms.VacancyForm;
import ru.itis.headhunter.dto.mappers.AccountMapper;
import ru.itis.headhunter.dto.mappers.VacancyMapper;
import ru.itis.headhunter.dto.mappers.VacancyResponseMapper;
import ru.itis.headhunter.dto.pages.VacanciesPageDto;
import ru.itis.headhunter.exceptions.NotCompanyAccountException;
import ru.itis.headhunter.exceptions.VacancyNotFoundException;
import ru.itis.headhunter.exceptions.VacancyResponseNotFoundException;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.Vacancy;
import ru.itis.headhunter.models.VacancyResponse;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.repositories.VacancyRepository;
import ru.itis.headhunter.repositories.VacancyResponseRepository;
import ru.itis.headhunter.security.details.AccountUserDetails;
import ru.itis.headhunter.services.VacancyService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final AccountsRepository accountsRepository;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final VacancyResponseMapper vacancyResponseMapper;
    private final VacancyResponseRepository vacancyResponseRepository;
    private final AccountMapper accountMapper;

    @Value("${headhunter.default-page-size}")
    private int defaultPageSize;

    @Override
    public VacancyDto addVacancy(VacancyForm vacancyForm) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        if(!account.getRole().equals(Account.Role.EMPLOYER)) {
            throw new NotCompanyAccountException("this account without such company");
        }

        Vacancy vacancy = Vacancy.builder()
                .title(vacancyForm.getTitle())
                .company(account.getCompany())
                .description(vacancyForm.getDescription())
                .salary(vacancyForm.getSalary())
                .specialization(Vacancy.Specialization.valueOf(vacancyForm.getSpecialization().name()))
                .build();

        account.getCompany().getVacancies().add(vacancy);

        return vacancyMapper.toVacancyDto(vacancyRepository.save(vacancy));
    }

    @Override
    public VacanciesPageDto getSortVacancies(Long page, Optional<String> sortBy, Optional<Long> salaryFrom, Optional<Long> salaryTo) {
        PageRequest pageRequest = PageRequest.of(page.intValue(), defaultPageSize, Sort.by(sortBy.orElse("id")).ascending());
        return vacancyMapper.toVacanciesPageDto(vacancyRepository
                .findAllBySalaryGreaterThanEqualAndSalaryLessThanEqual(pageRequest,
                        salaryFrom.orElse(0L), salaryTo.orElse(1000000L)));
    }
    @Override
    public VacanciesPageDto getAllByCompany(Long companyId, Long page, Optional<String> sortBy) {
        PageRequest pageRequest = PageRequest.of(page.intValue(), defaultPageSize, Sort.by(sortBy.orElse("id")).ascending());
        return vacancyMapper.toVacanciesPageDto(vacancyRepository.findAllByCompanyId(companyId, pageRequest));
    }

    @Override
    public VacancyResponseDto respondToVacancy(Long vacancyId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new VacancyNotFoundException("no such vacancy"));

        VacancyResponse vacancyResponse = VacancyResponse.builder()
                .vacancy(vacancy)
                .account(account)
                .build();

        return vacancyResponseMapper.toVacancyResponseDto(vacancyResponseRepository.save(vacancyResponse));
    }

    @Override
    public AccountDto acceptVacancyResponse(Long vacancyResponseId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        VacancyResponse vacancyResponse = vacancyResponseRepository.findById(vacancyResponseId)
                .orElseThrow(() -> new VacancyResponseNotFoundException("no such vacancy response"));

        if(!account.getRole().equals(Account.Role.EMPLOYER) ||
                !account.getCompany().getId().equals(vacancyResponse.getVacancy().getCompany().getId())) {
            throw new NotCompanyAccountException("this account without such company");
        }

        vacancyResponse.getAccount().setCompany(vacancyResponse.getVacancy().getCompany());
        vacancyResponse.getAccount().setRole(Account.Role.EMPLOYER);

        return accountMapper.toAccountDto(accountsRepository.save(vacancyResponse.getAccount()));
    }

    @Override
    public VacancyDto addVacancyToFavorites(Long vacancyId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new VacancyNotFoundException("no such vacancy"));

        account.getFavoritesVacancies().add(vacancy);
        accountsRepository.save(account);

        return vacancyMapper.toVacancyDto(vacancy);
    }

    @Override
    public VacanciesPageDto getAllBySpecialization(String specialization, Long page, Optional<String> sortBy) {
        PageRequest pageRequest = PageRequest.of(page.intValue(), defaultPageSize, Sort.by(sortBy.orElse("id")).ascending());
        return vacancyMapper.toVacanciesPageDto(vacancyRepository
                .findAllBySpecialization(Vacancy.Specialization.valueOf(specialization), pageRequest));
    }
}
