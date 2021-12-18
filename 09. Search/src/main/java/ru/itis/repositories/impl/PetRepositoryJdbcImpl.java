package ru.itis.repositories.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.model.Pet;
import ru.itis.model.User;
import ru.itis.repositories.PetRepository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class PetRepositoryJdbcImpl implements PetRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from pets";
    private final static String SQL_INSERT = "insert into pets (kind, name) VALUES (:kind, :name)";
    private final static String SQL_SELECT_BY_ID = "select * from pets where id = :id";
    private final String SQL_UPDATE_BY_ID = "update pets set kind = :kind, name = :name where id = :id";
    private final String SQL_DELETE_BY_ID = "delete from pets where id = :id";

    private final RowMapper<Pet> userRowMapper = (row, rowNumber) -> Pet.builder()
            .id(row.getInt("id"))
            .kind(row.getString("kind"))
            .name(row.getString("name"))
            .build();

    public PetRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<Pet> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Pet> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Pet save(Pet pet) {
        if(pet.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            Map<String, Object> values = new HashMap<>();
            values.put("kind", pet.getKind());
            values.put("name", pet.getName());

            SqlParameterSource parameterSource = new MapSqlParameterSource(values);

            jdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});
            pet.setId(keyHolder.getKeyAs(Integer.class));
        } else {
            update(pet.getId(), pet);
        }
        return pet;
    }

    @Override
    public void update(Integer id, Pet pet) {
        try {
            Map<String, Object> values = new HashMap<>();
            values.put("kind", pet.getKind());
            values.put("name", pet.getName());

            SqlParameterSource parameterSource = new MapSqlParameterSource(values);

            jdbcTemplate.update(SQL_UPDATE_BY_ID, parameterSource);
        } catch (DataAccessException e) {
            save(pet);
        }
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,
                Collections.singletonMap("id", id));
    }
}
