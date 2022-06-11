package ru.itis.headhunter.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.CompanyDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompaniesPageDto {
    private List<CompanyDto> companies;
    private Integer totalPages;
}
