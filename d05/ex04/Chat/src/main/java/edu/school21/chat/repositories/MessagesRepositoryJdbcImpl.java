package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    public static final int IDX_COLUMN = 1;

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

    @Override
    public void save(Message message) throws NotSavedSubEntityException {

        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery("SELECT id FROM chat.users");

            Long id = getIdLastElement(resultSet);

            User user = message.getAuthor();

            if (user.getId() == null || user.getId() > id || user.getId() < 1){
                throw new NotSavedSubEntityException("User id does not exist");
            }

            resultSet = statement.executeQuery("SELECT id FROM chat.chatrooms");

            id = getIdLastElement(resultSet);

            Chatroom chatroom = message.getChatroom();

            if (chatroom.getId() == null || chatroom.getId() > id || chatroom.getId() < 1){
                throw new NotSavedSubEntityException("Chatroom id does not exist");
            }

            connection.setSchema("chat");

            String queryInsert = "INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setLong(1, user.getId());
                preparedStatement.setLong(2, chatroom.getId());
                preparedStatement.setString(3, message.getText());

                try{
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDate()));
                } catch (NullPointerException e){
                    preparedStatement.setTimestamp(4, null);
                }

                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                message.setId(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
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
    }

    private Long getIdLastElement(ResultSet resultSet){
        try {
            while (!resultSet.isLast()) {
                resultSet.next();
            }
            return resultSet.getLong(IDX_COLUMN);
        } catch (SQLException e){
            System.err.println("Failed to get last index");
        }
        return 0L;
    }

    @Override
    public void update(Message message) throws NotSavedSubEntityException {

        PreparedStatement preparedStatement = null;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM chat.users")) {

            Long id = getIdLastElement(resultSet);

            User user = message.getAuthor();

            if (user.getId() == null || user.getId() > id || user.getId() < 1){
                throw new NotSavedSubEntityException("User id does not exist");
            }

            connection.setSchema("chat");

            String queryUpdate = "UPDATE chat.messages SET author = ?, room = ?, message_text = ?, message_time = ? WHERE chat.messages.id = ?;";

            preparedStatement = connection.prepareStatement(queryUpdate);

            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getText());

            try{
                preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDate()));
            } catch (NullPointerException e){
                preparedStatement.setTimestamp(4, null);
            }

            preparedStatement.setLong(5, message.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("" + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
