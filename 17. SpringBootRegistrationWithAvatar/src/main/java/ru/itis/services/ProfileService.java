package ru.itis.services;

import ru.itis.dto.AccountDto;

import java.util.Optional;

public interface ProfileService {
    Optional<AccountDto> getAccountByLogin(String login);
}
