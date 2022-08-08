package d01.ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    Node removeTransactionById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
}
