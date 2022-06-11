package ru.itis.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.Vacancy;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyResponseDto {
    public enum State {
        ACTIVE, DELETED
    }

    private Long id;
    private Long vacancyId;
    private Long accountId;
}
