package ru.itis.repositories;

import ru.itis.model.Profile;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile>{
    List<Profile> findByEmail(String email);
}
