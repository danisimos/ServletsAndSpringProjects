package ru.itis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.models.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Account getAccountByConfirmCode(String confirmCode);
}
