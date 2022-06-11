package ru.itis.headhunter.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.VacancyDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacanciesPageDto {
    private List<VacancyDto> vacancies;
    private Integer totalPages;
}
