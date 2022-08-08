package d01.ex00;

public class User {

    private int identifier;
    private String name;
    private int balance;

    User(int identifier, String name, int balance){
        this.identifier = identifier;
        this.name = name;

        if (balance >= 0){
            this.balance = balance;
        }else{
            System.out.println("Warning: Balance cannot be negative. Set to 0 by default");
            this.balance = 0;
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance >= 0){
            this.balance = balance;
        }else{
            System.out.println("Warning: Balance cannot be negative. Set to 0 by default");
            this.balance = 0;
        }
    }
}
