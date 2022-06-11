package ru.itis.headhunter.services;

import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.models.Account;

public interface EmailService {
    void sendConfirmCode(Account account);

    AccountDto confirm(String code);

    AccountDto sendAnotherConfirmCode(String code);
}
