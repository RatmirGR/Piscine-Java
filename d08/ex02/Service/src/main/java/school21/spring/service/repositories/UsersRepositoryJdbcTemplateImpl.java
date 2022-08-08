package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import school21.spring.service.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("driverManagerDataSource")DataSource dataSource){
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
        String sql = "INSERT INTO users VALUES(:id, :email, :password);";

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);

        this.jdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET email = :email WHERE id = :id;";

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);

        this.jdbcTemplate.update(sql, namedParameters);
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
