package ru.itis.headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VacancyResponse {
    public enum State {
        ACTIVE, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vacancy")
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
