package ru.itis.headhunter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.headhunter.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findAll(Pageable pageable);
}
