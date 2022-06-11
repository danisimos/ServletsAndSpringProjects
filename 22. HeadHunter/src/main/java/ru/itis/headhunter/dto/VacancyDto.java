package ru.itis.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.Company;
import ru.itis.headhunter.models.Vacancy;
import ru.itis.headhunter.models.VacancyResponse;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyDto {
    public enum State {
        ACTIVE, DELETED
    }

    public enum Specialization {
        IT, SALES, MANAGEMENT, SCIENCE, MEDICINE
    }

    private Long id;
    private Long salary;
    private Specialization specialization;
    private Timestamp createdAt;
    private String title;
    private String description;
}
