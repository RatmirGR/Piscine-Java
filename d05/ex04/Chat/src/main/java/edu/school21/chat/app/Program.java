package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");

            runQueriesFromFile(dataSource, "../src/main/resources/schema.sql");
            runQueriesFromFile(dataSource, "../src/main/resources/data.sql");

            UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
            List<User> userList = usersRepository.findAll(2, 3);
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    public static void runQueriesFromFile(HikariDataSource dataSource, String filename) {
        try (Connection connection = dataSource.getConnection();
             Scanner scanner = new Scanner(new File(filename)).useDelimiter(";")) {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}