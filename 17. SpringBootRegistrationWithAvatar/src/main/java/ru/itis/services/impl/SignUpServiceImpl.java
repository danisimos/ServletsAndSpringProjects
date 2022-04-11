package ru.itis.services.impl;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.services.ConfirmService;
import ru.itis.services.SignUpService;
import ru.itis.util.EmailUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;
    private final ConfirmService confirmService;

    private final EmailUtil emailUtil;

    @Transactional
    @Override
    public void signUp(SignUpDto accountForm) throws IOException, TemplateException {
        Account account = Account.builder()
                .login(accountForm.getLogin())
                .firstName(accountForm.getFirstName())
                .lastName(accountForm.getLastName())
                .email(accountForm.getEmail())
                .state(Account.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .password(accountForm.getPassword())
                .build();

        accountsRepository.save(account);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", account.getFirstName());
        data.put("confirmCode", account.getConfirmCode());

        emailUtil.sendMail(account.getEmail(), "confirm", "email_confirmation.ftlh", data);

        confirmService.addConfirmCode(account.getConfirmCode(), System.currentTimeMillis());
    }
}
