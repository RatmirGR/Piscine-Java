package d01.ex02;

public class Program {

    private static final int TEST_SIZE = 10;

    public static void main(String[] args) throws UserNotFoundException{
        UsersArrayList usersList = new UsersArrayList();

        System.out.println("Adding users to user array");
        for (int i = 0; i < TEST_SIZE; i++) {
            usersList.addUser(new User("Test" + (i + 1), i * 10));
        }

        System.out.println("Getting user by index");
        for (int i = 0; i < usersList.retrieveNumOfUsers(); i++) {
            System.out.println("-> id: " + usersList.retrieveUserByIndex(i).getIdentifier());
        }

        System.out.println("\nGetting user by id");
        for (int i = 0; i < usersList.retrieveNumOfUsers(); i++) {
            System.out.println("-> name: " + usersList.retrieveUserById(i + 1).getName());
        }

        System.out.println("\nGetting the number of users before adding 11 user");
        System.out.println("-> Number of users: " + usersList.retrieveNumOfUsers());

        usersList.addUser(new User("Mike", 100));

        System.out.println("Getting the number of users after adding 11 user");
        System.out.println("-> Number of users: " + usersList.retrieveNumOfUsers());
        System.out.println("-> Number of users: " + usersList.retrieveUserById(11).getName());


        System.out.println("---------------------------------");

        System.out.println("\nTrying to get user by id");
        System.out.println("-> Number of users: " + usersList.retrieveUserById(12).getName());
    }
}
