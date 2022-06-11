package ru.itis.headhunter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.headhunter.models.Vacancy;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findAllByCompanyId(Long companyId, Pageable pageable);
    Page<Vacancy> findAllBySpecialization(Vacancy.Specialization specialization, Pageable pageable);
    Page<Vacancy> findAllBySalaryGreaterThanEqualAndSalaryLessThanEqual(Pageable pageable, Long salaryFrom, Long salaryTo);
}
