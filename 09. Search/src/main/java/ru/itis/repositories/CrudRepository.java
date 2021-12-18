package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T>{
    List<T> findAll();
    T save(T entity);
    Optional<T> findById(Integer id);
    void update(Integer id, T entity);
    void delete(Integer id);
}
