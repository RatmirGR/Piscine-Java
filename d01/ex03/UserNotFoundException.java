package d01.ex03;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User is not found");
    }
}
