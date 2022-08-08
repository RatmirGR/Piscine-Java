package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static final Long ID_ELEMENT = 3L;

    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");

            runQueriesFromFile(dataSource, "../src/main/resources/schema.sql");
            runQueriesFromFile(dataSource, "../src/main/resources/data.sql");

            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            Optional<Message> messageOptional = messagesRepository.findById(ID_ELEMENT);

            if (messageOptional.isPresent()) {
                System.out.println("==== Message status before update ====");
                Message message = messageOptional.get();
                System.out.println(message.getId());
                System.out.println(message.getText());

                message.getAuthor().setId(ID_ELEMENT);
                message.setText("Bye");
                message.setDate(null);

                message.setAuthor(new User(4L, "Sam", "vs", new ArrayList<>(), new ArrayList<>()));
                Message optional = messagesRepository.findById(ID_ELEMENT).orElse(null);
                if (!optional.equals(Optional.empty())){
                    System.out.println(optional);
                }
                try {
                    messagesRepository.update(message);
                } catch (NotSavedSubEntityException e){
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Error: Message not found");
            }

            messageOptional = messagesRepository.findById(ID_ELEMENT);
            if (messageOptional.isPresent()) {
                System.out.println("\n==== Message status after update ====");
                Message message2 = messageOptional.get();
                System.out.println(message2.getId());
                System.out.println(message2.getText());
                Message optional = messagesRepository.findById(ID_ELEMENT).orElse(null);
                if (!optional.equals(Optional.empty())){
                    System.out.println(optional);
                }
            } else {
                System.err.println("Error: Update message not found");
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