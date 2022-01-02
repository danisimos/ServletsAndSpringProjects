package ru.itis.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.Profile;
import ru.itis.repositories.ProfileRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepositoryJpaImpl implements ProfileRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Profile> findAll() {
        Query query = entityManager.createQuery("select profile from Profile profile");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Profile save(Profile entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public Optional<Profile> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Integer id, Profile entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Profile> findByEmail(String email) {
        String param = "%" + email + "%";
        TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile where profile.email like :param", Profile.class);
        query.setParameter("param", param);

        return query.getResultList();
    }
}
