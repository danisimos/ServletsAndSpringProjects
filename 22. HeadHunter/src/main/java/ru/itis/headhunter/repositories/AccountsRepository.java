package ru.itis.headhunter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.headhunter.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByConfirmCode(String confirmCode);
}
