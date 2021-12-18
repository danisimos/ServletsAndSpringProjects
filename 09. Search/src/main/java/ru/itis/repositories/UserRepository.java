package ru.itis.repositories;

import ru.itis.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User>{
    List<User> findByEmail(String email);
}
