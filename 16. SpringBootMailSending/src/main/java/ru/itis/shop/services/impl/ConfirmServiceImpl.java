package ru.itis.shop.services.impl;

import java.time.Duration;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shop.models.Account;
import ru.itis.shop.repositories.AccountsRepository;
import ru.itis.shop.services.ConfirmService;

@Service
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {
    private final Map<String, Long> confirmCodes = Collections.synchronizedMap(new HashMap<>());
    private final AccountsRepository accountsRepository;
    private final static long interval = 60000;

    @Override
    public boolean confirm(String confirmCode) {
        long endTimestamp = confirmCodes.get(confirmCode);

        if(System.currentTimeMillis() - endTimestamp > interval) {
            confirmCodes.put(confirmCode, System.currentTimeMillis());
            return false;
        }

        confirmCodes.remove(confirmCode);
        Account account = accountsRepository.getAccountByConfirmCode(confirmCode);
        account.setState(Account.State.CONFIRMED);

        accountsRepository.save(account);
        return true;
    }

    @Override
    public void addConfirmCode(String confirmCode, long timestamp) {
        confirmCodes.put(confirmCode, timestamp);
    }
}
