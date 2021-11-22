package ru.itis.repositories.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from accounts";
    private final static String SQL_INSERT = "insert into accounts (login, first_name, last_name, age, password) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from accounts where id = ?";
    private final static String SQL_SELECT_BY_LOGIN = "select * from accounts where login = ?";
    private final String SQL_UPDATE_BY_ID = "update accounts set login = ?, first_name = ?, last_name = ?, age = ?, password = ? where id = ?";
    private final String SQL_DELETE_BY_ID = "delete from accounts where id = ?";

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getInt("id"))
            .login(row.getString("login"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .password(row.getString("password"))
            .build();

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, userRowMapper, login));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public User save(User user) {
        if(user.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setInt(4, user.getAge());
                statement.setString(5, user.getPassword());

                return statement;
            }, keyHolder);
            user.setId(keyHolder.getKey().intValue());
        } else {
            update(user.getId(), user);
        }
        return user;
    }

    @Override
    public void update(Integer id, User user) {
        try {
            jdbcTemplate.update(SQL_UPDATE_BY_ID,
                    user.getLogin(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAge(),
                    user.getPassword(),
                    id);
        } catch (DataAccessException e) {
            save(user);
        }
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
