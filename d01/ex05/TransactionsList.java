package d01.ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    Transaction removeTransactionById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
    Transaction retrieveTransactionById(UUID id) throws TransactionNotFoundException;
    boolean isTransIdInTransList(UUID id) throws TransactionNotFoundException;
}
