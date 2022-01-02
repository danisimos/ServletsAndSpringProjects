package ru.itis.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.Pet;
import ru.itis.repositories.PetRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryJpaImpl implements PetRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pet> findAll() {
        Query query = entityManager.createQuery("select pet from Pet pet");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Pet save(Pet entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public Optional<Pet> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Integer id, Pet entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
