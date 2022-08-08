package d01.ex05;

public class UserIdsGenerator {
    private int identifier = 0;
    private static UserIdsGenerator instance = null;

    private UserIdsGenerator(){}

    public static UserIdsGenerator getInstance(){
        if (instance == null){
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId(){
        return identifier += 1;
    }
}

