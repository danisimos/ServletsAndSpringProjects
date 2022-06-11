package ru.itis.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.CV;
import ru.itis.headhunter.models.Company;
import ru.itis.headhunter.models.VacancyResponse;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        APPLICANT, EMPLOYER
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private State state;
    private Role role;
}
