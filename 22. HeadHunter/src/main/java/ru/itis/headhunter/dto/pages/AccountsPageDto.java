package ru.itis.headhunter.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.dto.AccountDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsPageDto {
    private List<AccountDto> accounts;
    private Integer totalPages;
}

