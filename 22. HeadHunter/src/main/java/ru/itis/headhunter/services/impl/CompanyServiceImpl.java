package ru.itis.headhunter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.headhunter.dto.CompanyDto;
import ru.itis.headhunter.dto.forms.CompanyForm;
import ru.itis.headhunter.dto.mappers.CompanyMapper;
import ru.itis.headhunter.dto.pages.CompaniesPageDto;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.Company;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.repositories.CompanyRepository;
import ru.itis.headhunter.security.details.AccountUserDetails;
import ru.itis.headhunter.services.CompanyService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final AccountsRepository accountsRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Value("${headhunter.default-page-size}")
    private int defaultPageSize;

    @Override
    public CompanyDto createCompany(CompanyForm companyForm) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        Company company = Company.builder()
                .description(companyForm.getDescription())
                .name(companyForm.getName())
                .accounts(Collections.singletonList(account))
                .build();

        CompanyDto companyDto = companyMapper.toCompanyDto(companyRepository.save(company));

        account.setCompany(company);
        account.setRole(Account.Role.EMPLOYER);
        accountsRepository.save(account);

        return companyDto;
    }

    @Override
    public CompaniesPageDto getAllCompanies(Long page, Optional<String> sortBy) {
        PageRequest pageRequest = PageRequest.of(page.intValue(), defaultPageSize, Sort.by(sortBy.orElse("id")).ascending());
        return companyMapper.toCompaniesPageDto(companyRepository.findAll(pageRequest));
    }
}
