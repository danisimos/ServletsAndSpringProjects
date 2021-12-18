package ru.itis.repositories.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from accounts";
    private final static String SQL_INSERT = "insert into accounts (first_name, last_name, age, email) VALUES (:firstName, :lastName, :age, :email)";
    private final static String SQL_SELECT_BY_ID = "select * from accounts where id = :id";
    private final String SQL_UPDATE_BY_ID = "update accounts set first_name = :firstName, last_name = :lastName, age = :age, email = :email where id = :id";
    private final String SQL_DELETE_BY_ID = "delete from accounts where id = :id";
    private final String SQL_FIND_BY_EMAIL = "select * from accounts where email like :email";

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .email(row.getString("email"))
            .build();

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByEmail(String email) {
        return jdbcTemplate.query(SQL_FIND_BY_EMAIL,
                Collections.singletonMap("email", "%" + email + "%"), userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public User save(User user) {
        if(user.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            Map<String, Object> values = new HashMap<>();
            values.put("firstName", user.getFirstName());
            values.put("lastName", user.getLastName());
            values.put("age", user.getAge());
            values.put("email", user.getEmail());

            SqlParameterSource parameterSource = new MapSqlParameterSource(values);

            jdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});
            user.setId(keyHolder.getKeyAs(Integer.class));
        } else {
            update(user.getId(), user);
        }
        return user;
    }

    @Override
    public void update(Integer id, User user) {
        try {
            Map<String, Object> values = new HashMap<>();
            values.put("firstName", user.getFirstName());
            values.put("lastName", user.getLastName());
            values.put("age", user.getAge());
            values.put("email", user.getEmail());

            SqlParameterSource parameterSource = new MapSqlParameterSource(values);

            jdbcTemplate.update(SQL_UPDATE_BY_ID, parameterSource);
        } catch (DataAccessException e) {
            save(user);
        }
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,
                Collections.singletonMap("id", id));
    }
}
