package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Category;
import ru.itis.models.Profile;

import java.util.List;

@Repository
public interface CategoriesRepositoryJpa extends JpaRepository<Category, Integer> {
    Category save(Category category);
    List<Category> findAll();
    Category getById(Integer id);
    List<Category> findAllByProfilesContains(Profile profile);

}
