package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Profile;

import java.util.List;

@Repository
public interface ProfilesRepositoryJpa extends JpaRepository<Profile, Integer> {
    Profile save(Profile profile);
    List<Profile> findAll();
    Profile getById(Integer id);
}
