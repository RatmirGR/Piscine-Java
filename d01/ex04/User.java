package d01.ex04;

public class User {

    private final int identifier;
    private String name;
    private int balance;
    private final TransactionsList transactions;

    public User(String name, int balance){
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        transactions = new TransactionsLinkedList();

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

    public TransactionsList getTransactions() {
        return transactions;
    }
}