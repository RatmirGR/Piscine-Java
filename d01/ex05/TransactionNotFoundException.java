package d01.ex05;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        super("Transaction not found");
    }
}
