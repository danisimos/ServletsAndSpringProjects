package ru.itis.repositories;

import ru.itis.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Integer id);
    List<User> findAll();
    User save(User user);
    void update(Integer id, User user);
    void delete(Integer id);
}
