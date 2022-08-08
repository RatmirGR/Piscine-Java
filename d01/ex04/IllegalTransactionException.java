package d01.ex04;

public class IllegalTransactionException extends RuntimeException{
    public IllegalTransactionException(String description){
        super(description);
    }
}
