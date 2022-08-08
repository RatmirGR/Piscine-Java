package edu.school21.repositories;

import edu.school21.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id = :id;",
                Collections.singletonMap("id", id),
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public List<User> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM users;", new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(
                "INSERT INTO users VALUES(:id, :email);",
                new BeanPropertySqlParameterSource(entity)
        );
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(
                "UPDATE users SET email = :email WHERE id = :id;",
                new BeanPropertySqlParameterSource(entity)
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id = :id;",
                Collections.singletonMap("id", id)
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE email = :email;",
                Collections.singletonMap("email", email),
                new BeanPropertyRowMapper<>(User.class)
        ));
    }
}
