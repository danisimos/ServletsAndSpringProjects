package ru.itis.shop.services.impl;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shop.models.Account;
import ru.itis.shop.repositories.AccountsRepository;
import ru.itis.shop.services.SendEmailService;
import ru.itis.shop.util.EmailUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {
    private final EmailUtil emailUtil;
    private final AccountsRepository accountsRepository;

    @Override
    public void send(String confirmCode) throws TemplateException, IOException {
        Account account = accountsRepository.getAccountByConfirmCode(confirmCode);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", account.getFirstName());
        data.put("confirmCode", account.getConfirmCode());

        emailUtil.sendMail(account.getEmail(), "confirm", "email_confirmation.ftlh", data);
    }
}
