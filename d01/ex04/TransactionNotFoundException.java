package d01.ex04;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        super("Transaction not found");
    }
}
