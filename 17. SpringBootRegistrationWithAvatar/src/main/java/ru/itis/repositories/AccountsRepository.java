package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Account getAccountByConfirmCode(String confirmCode);
    Optional<Account> findByLogin(String login);
}
