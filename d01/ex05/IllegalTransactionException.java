package d01.ex05;

public class IllegalTransactionException extends RuntimeException{
    public IllegalTransactionException(String description){
        super(description);
    }
}
