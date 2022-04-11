package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.dto.FileLinkDto;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.services.ProfileService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final AccountsRepository accountsRepository;

    @Override
    public Optional<AccountDto> getAccountByLogin(String login) {
        Optional<Account> accountOptional = accountsRepository.findByLogin(login);
        if(accountOptional.isEmpty()) return Optional.empty();

        Account account = accountOptional.get();

        return Optional.of(AccountDto.builder()
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .state(account.getState())
                .id(account.getId())
                .login(account.getLogin())
                .avatarLink(new FileLinkDto(account.getAvatarLink()))
                .build());
    }
}
