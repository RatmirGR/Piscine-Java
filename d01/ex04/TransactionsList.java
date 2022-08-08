package d01.ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    Transaction removeTransactionById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
    Transaction retrieveTransactionById(UUID id) throws TransactionNotFoundException;
}
