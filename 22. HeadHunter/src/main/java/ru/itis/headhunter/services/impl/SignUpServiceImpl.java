package ru.itis.headhunter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.forms.SignUpForm;
import ru.itis.headhunter.dto.mappers.AccountMapper;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.services.EmailService;
import ru.itis.headhunter.services.SignUpService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public AccountDto signUp(SignUpForm signUpForm) {
        Account newAccount = Account.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.APPLICANT)
                .build();

        accountsRepository.save(newAccount);

        emailService.sendConfirmCode(newAccount);

        return accountMapper.toAccountDto(newAccount);
    }
}

