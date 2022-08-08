package edu.school21.repositories;

import edu.school21.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return new User(resultSet.getLong(1), resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;");
            ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    userList.add(new User(resultSet.getLong(1), resultSet.getString(2)));
                }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (email) VALUES (?);")){
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email = ? WHERE id = ?;")){
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?;")){
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }


}
