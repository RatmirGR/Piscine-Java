package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try(HikariDataSource dataSource = new HikariDataSource();
            Scanner scanner = new Scanner(System.in)) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");

            runQueriesFromFile(dataSource, "../src/main/resources/schema.sql");
            runQueriesFromFile(dataSource, "../src/main/resources/data.sql");

            System.out.println("Enter a message ID");

            if (scanner.hasNextLong()) {
                Long id = scanner.nextLong();
                MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
                Message message = messagesRepository.findById(id).orElse(null);
                System.out.println(message);
            } else {
                System.err.println("Please enter a valid id");
                System.exit(-1);
            }
        }
    }

    public static void runQueriesFromFile(HikariDataSource dataSource, String filename) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(new File(filename)).useDelimiter(";")) {

            while (scanner.hasNext()) {
                statement.execute(scanner.next().trim());
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("" + e.getMessage());
        }
    }
}