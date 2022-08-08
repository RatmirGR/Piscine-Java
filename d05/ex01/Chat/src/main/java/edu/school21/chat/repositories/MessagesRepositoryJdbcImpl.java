package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id){

        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){

            resultSet = statement.executeQuery("SELECT * FROM chat.messages WHERE id = " + id);
            resultSet.next();

            Long messageId = resultSet.getLong("id");
            long authorId = resultSet.getLong("author");
            long roomId = resultSet.getLong("room");
            String message = resultSet.getString("message_text");
            Timestamp time = resultSet.getTimestamp("message_time");

            LocalDateTime dateTime = null;
            if (time != null){
                dateTime = resultSet.getTimestamp("message_time").toLocalDateTime();
            }

            resultSet = statement.executeQuery("SELECT * FROM chat.users WHERE id = " + authorId);
            resultSet.next();

            User user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    null,
                    null
            );

            resultSet = statement.executeQuery("SELECT * FROM chat.chatrooms WHERE id = " + roomId);
            resultSet.next();

            Chatroom chatroom = new Chatroom(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    null,
                    null
            );

            return Optional.of(new Message(messageId, user, chatroom, message, dateTime));

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        return Optional.empty();
    }
}
