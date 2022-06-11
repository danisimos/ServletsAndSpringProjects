package ru.itis.headhunter.services;

import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.forms.SignUpForm;

public interface SignUpService {
    AccountDto signUp(SignUpForm signUpForm);
}
