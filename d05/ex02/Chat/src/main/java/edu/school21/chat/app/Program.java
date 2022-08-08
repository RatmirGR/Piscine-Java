package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.NotSavedSubEntityException;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try(HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");

            runQueriesFromFile(dataSource, "../src/main/resources/schema.sql");
            runQueriesFromFile(dataSource, "../src/main/resources/data.sql");

            User creator = new User(4L, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;
            Chatroom room = new Chatroom(4L, "room", creator, new ArrayList());
            Message message = new Message(null, author, room, "Hello22!", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            messagesRepository.save(message);
            System.out.println(message.getId());
        }catch (NotSavedSubEntityException e){
            System.err.println(e.getMessage());
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