package ru.itis.headhunter.dto.mappers;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.pages.AccountsPageDto;
import ru.itis.headhunter.models.Account;

import java.util.List;

@Mapper
public abstract class AccountMapper {
    public abstract AccountDto toAccountDto(Account account);
    public abstract Account toAccount(AccountDto accountDto);
    public abstract List<AccountDto> toAccountDtoList(List<Account> accounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateAccountFromDto(AccountDto accountDto, @MappingTarget Account account);

    public AccountsPageDto toAccountsPageDto(Page<Account> accounts) {
        return AccountsPageDto.builder()
                .accounts(toAccountDtoList(accounts.getContent()))
                .totalPages(accounts.getTotalPages())
                .build();
    }
}
