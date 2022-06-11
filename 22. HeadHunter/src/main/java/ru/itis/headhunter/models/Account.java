package ru.itis.headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {
    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        APPLICANT, EMPLOYER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Column(name = "confirm_code_expiration")
    private Timestamp confirmCodeExpiration;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @OneToMany(mappedBy = "account")
    private List<CV> cvList;

    @OneToMany(mappedBy = "account")
    private List<VacancyResponse> vacancyResponses;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id", referencedColumnName = "id"))
    private Set<Vacancy> favoritesVacancies;
}
