package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> chatroomList;
    private List<Chatroom> activeChatroomList;

    public User(Long id, String login, String password, List<Chatroom> chatroomList, List<Chatroom> activeChatroomList){
        this.id = id;
        this.login = login;
        this.password = password;
        this.chatroomList = chatroomList;
        this.activeChatroomList = activeChatroomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getChatroomList() {
        return chatroomList;
    }

    public void setChatroomList(List<Chatroom> chatroomList) {
        this.chatroomList = chatroomList;
    }

    public List<Chatroom> getActiveChatroomList() {
        return activeChatroomList;
    }

    public void setActiveChatroomList(List<Chatroom> activeChatroomList) {
        this.activeChatroomList = activeChatroomList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id)
                && login.equals(user.login)
                && password.equals(user.password)
                && chatroomList.equals(user.chatroomList)
                && activeChatroomList.equals(user.activeChatroomList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, chatroomList, activeChatroomList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatroomList=" + chatroomList +
                ", activeChatroomList=" + activeChatroomList +
                '}';
    }
}
