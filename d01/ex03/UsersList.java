package d01.ex03;

public interface UsersList {
    void addUser(User user);
    User retrieveUserById(int id) throws UserNotFoundException;
    User retrieveUserByIndex(int index);
    int retrieveNumOfUsers();
}
