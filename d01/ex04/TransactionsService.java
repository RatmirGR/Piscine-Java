package d01.ex04;

import java.util.UUID;

public class TransactionsService {
    private final UsersList usersList;
    private final TransactionsLinkedList unpairedTransactions;

    public TransactionsService(){
        usersList = new UsersArrayList();
        unpairedTransactions = new TransactionsLinkedList();
    }

    public void addUser(User user){
        usersList.addUser(user);
    }

    public int retrieveUserBalance(int userId) throws UserNotFoundException{
        return usersList.retrieveUserById(userId).getBalance();
    }

    public void transferTransaction(int recipientId, int senderId, int transferAmount) throws UserNotFoundException, IllegalTransactionException {

        if (recipientId == senderId){
            throw new IllegalTransactionException("Sender id and recipient id are the same");
        }

        if (transferAmount < 0){
            throw new IllegalTransactionException("Transaction amount cannot be negative");
        }

        User sender = usersList.retrieveUserById(senderId);

        if (sender.getBalance() - transferAmount < 0) {
            throw new IllegalTransactionException("Transfer amount exceeds balance amount");
        }

        User recipient = usersList.retrieveUserById(recipientId);

        Transaction transDebit = new Transaction(recipient, sender, Transaction.Category.DEBIT, transferAmount);
        Transaction transCredit = new Transaction(sender, recipient, Transaction.Category.CREDIT, -transferAmount);

        transCredit.setIdentifier(transDebit.getIdentifier());

        recipient.setBalance(recipient.getBalance() + transDebit.getTransferAmount());
        sender.setBalance(sender.getBalance() + transCredit.getTransferAmount());

        recipient.getTransactions().addTransaction(transDebit);
        sender.getTransactions().addTransaction(transCredit);
    }

    public Transaction[] retrieveTransfers(int userId) throws UserNotFoundException{
        return usersList.retrieveUserById(userId).getTransactions().toArray();
    }

    public void removeTransaction(UUID transId, int userId) throws TransactionNotFoundException, UserNotFoundException{
        Transaction remoteTrans = usersList.retrieveUserById(userId).getTransactions().retrieveTransactionById(transId);

        if (!unpairedTransactions.isTransIdInTransList(transId)){
            int secondId = 0;

            if (remoteTrans.getRecipient().getIdentifier() == userId){
                secondId = remoteTrans.getSender().getIdentifier();
            }else if (remoteTrans.getSender().getIdentifier() == userId){
                secondId = remoteTrans.getRecipient().getIdentifier();
            }
            remoteTrans = usersList.retrieveUserById(secondId).getTransactions().retrieveTransactionById(transId);
            unpairedTransactions.addTransaction(remoteTrans);
        }else{
            unpairedTransactions.removeTransactionById(transId);
        }
        usersList.retrieveUserById(userId).getTransactions().removeTransactionById(transId);
    }

    public Transaction[] transactionVerification(){
        return unpairedTransactions.toArray();
    }
}
