package ru.itis.blogrestapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.blogrestapi.models.Author;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
