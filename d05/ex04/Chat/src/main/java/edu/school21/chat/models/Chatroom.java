package edu.school21.chat.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(Long id, String name, User owner, List<Message> messageList) throws SQLException{
        if (name == null){
            return;
        }
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id.equals(chatroom.id)
                && name.equals(chatroom.name)
                && owner.equals(chatroom.owner)
                && messageList.equals(chatroom.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messageList);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", messageList=" + messageList +
                '}';
    }
}
