package ru.itis.headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacancy {
    public enum State {
        ACTIVE, DELETED
    }

    public enum Specialization {
        IT, SALES, MANAGEMENT, SCIENCE, MEDICINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private Long salary;

    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @OneToMany(mappedBy = "vacancy")
    private List<VacancyResponse> vacancyResponses;

    @ManyToMany(mappedBy = "favoritesVacancies")
    private Set<Account> inFavorites;
}
