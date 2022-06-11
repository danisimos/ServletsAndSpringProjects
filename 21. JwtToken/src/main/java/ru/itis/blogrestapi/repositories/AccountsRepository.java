package ru.itis.blogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.blogrestapi.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
