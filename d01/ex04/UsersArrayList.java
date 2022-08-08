package d01.ex04;

public class UsersArrayList implements UsersList{

    private static int arraySize = 10;
    private int countNumUsers = 0;

    private User[] arrayUsers = new User[arraySize];

    @Override
    public void addUser(User user) {
        if (countNumUsers < arraySize){
            arrayUsers[countNumUsers] = user;
        }else{
            User[] tmpArr = new User[arraySize * 3 / 2];

            int i;
            for (i = 0; i < arraySize; i++) {
                tmpArr[i] = arrayUsers[i];
            }
            tmpArr[i] = user;
            arrayUsers = tmpArr;
            arraySize = arraySize * 3 / 2;
        }
        countNumUsers++;
    }

    @Override
    public User retrieveUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < countNumUsers; i++) {
            if (arrayUsers[i].getIdentifier() == id){
                return arrayUsers[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveUserByIndex(int index) {
        if (index >= 0 && index < countNumUsers) {
            return arrayUsers[index];
        }else{
            System.out.println("Warning: Index out of range. Enter a valid index");
            return null;
        }
    }

    @Override
    public int retrieveNumOfUsers() {
        return countNumUsers;
    }
}
