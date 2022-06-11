package ru.itis.headhunter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.headhunter.dto.CVDto;
import ru.itis.headhunter.dto.forms.CVForm;
import ru.itis.headhunter.dto.mappers.CVMapper;
import ru.itis.headhunter.dto.pages.CVPageDto;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.CV;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.repositories.CVRepository;
import ru.itis.headhunter.security.details.AccountUserDetails;
import ru.itis.headhunter.services.CVService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {
    private final AccountsRepository accountsRepository;
    private final CVRepository cvRepository;
    private final CVMapper cvMapper;

    @Value("${headhunter.default-page-size}")
    private int defaultPageSize;

    @Override
    public CVDto createCV(CVForm cvForm) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        CV cv = CV.builder()
                .account(account)
                .text(cvForm.getText())
                .title(cvForm.getTitle())
                .build();

        account.getCvList().add(cv);

        accountsRepository.save(account);

        return cvMapper.toCVDto(cvRepository.save(cv));
    }

    @Override
    public CVPageDto getAllCV(Long page, Optional<String> sortBy) {
        PageRequest pageRequest = PageRequest.of(page.intValue(), defaultPageSize, Sort.by(sortBy.orElse("id")).ascending());
        return cvMapper.toCVPageDto(cvRepository.findAll(pageRequest));
    }
}
