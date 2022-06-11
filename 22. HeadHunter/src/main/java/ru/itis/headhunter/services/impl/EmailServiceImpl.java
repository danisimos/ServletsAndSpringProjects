package ru.itis.headhunter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.mappers.AccountMapper;
import ru.itis.headhunter.exceptions.AccountNotFoundException;
import ru.itis.headhunter.exceptions.ConfirmCodeExpiredException;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.services.EmailService;
import ru.itis.headhunter.utils.EmailUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailUtils emailUtils;
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Override
    public void sendConfirmCode(Account account) {
        account.setConfirmCodeExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)));
        account.setConfirmCode(UUID.randomUUID().toString());
        accountsRepository.save(account);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", account.getFirstName());
        data.put("confirmCode", account.getConfirmCode());

        emailUtils.sendMail(account.getEmail(), "confirm", "email_confirmation.ftlh", data);
    }

    @Override
    public AccountDto confirm(String code) {
        Account account = accountsRepository.findByConfirmCode(code)
                .orElseThrow(() -> new AccountNotFoundException("no account with such confirm code"));

        if(account.getConfirmCodeExpiration().toLocalDateTime().isBefore(LocalDateTime.now())) {
            throw new ConfirmCodeExpiredException("confirm code is expired try to send another");
        }

        account.setState(Account.State.CONFIRMED);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto sendAnotherConfirmCode(String code) {
        Account account = accountsRepository.findByConfirmCode(code)
                .orElseThrow(() -> new AccountNotFoundException("no account with such confirm code"));

        account.setConfirmCodeExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)));

        Map<String, String> data = new HashMap<>();
        data.put("firstName", account.getFirstName());
        data.put("confirmCode", account.getConfirmCode());

        emailUtils.sendMail(account.getEmail(), "confirm", "email_confirmation.ftlh", data);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
