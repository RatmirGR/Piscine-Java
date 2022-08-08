package d01.ex03;

import java.util.UUID;

public class Program {

    private static final int NUM_TRANSACTION = 4;

    public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException{

        System.out.println("User creation");
        User userOne = new User("Tom", 500);
        User userTwo = new User("Tim", 1500);

        System.out.println("-> Name: " + userOne.getName() + ", balance: " + userOne.getBalance());
        System.out.println("-> Name: " + userTwo.getName() + ", balance: " + userTwo.getBalance());

        System.out.println("------------------------------------------");

        System.out.println("\nConducting transactions");
        Transaction transDebit = null, transCredit = null;

        for (int i = 0; i < NUM_TRANSACTION; i++) {
            transCredit = new Transaction(userTwo, userOne, Transaction.Category.CREDIT, -50);
            userTwo.getTransactions().addTransaction(transCredit);
            userTwo.setBalance(userTwo.getBalance() + transCredit.getTransferAmount());
            transDebit = new Transaction(userOne, userTwo, Transaction.Category.DEBIT, 50);
            userOne.getTransactions().addTransaction(transDebit);
            userOne.setBalance(userOne.getBalance() + transDebit.getTransferAmount());
        }

        System.out.println("-> Name: " + userOne.getName() + ", balance: " + userOne.getBalance());
        System.out.println("-> Name: " + userTwo.getName() + ", balance: " + userTwo.getBalance());

        System.out.println("------------------------------------------");

        System.out.println("\nGetting all user transactions (UserOne) before deleting");

        Transaction[] arrTransaction;

        arrTransaction = userOne.getTransactions().toArray();

        for (int i = 0; i < arrTransaction.length; i++) {
            System.out.println("-> Transaction IDs: " + arrTransaction[i].getIdentifier());
        }

        System.out.println("\nGetting all user transactions (UserTwo) before deleting");

        arrTransaction = userTwo.getTransactions().toArray();

        for (int i = 0; i < arrTransaction.length; i++) {
            System.out.println("-> Transaction IDs: " + arrTransaction[i].getIdentifier());
        }

        System.out.println("------------------------------------------");

        userTwo.getTransactions().removeTransactionById(transCredit.getIdentifier());
        userOne.getTransactions().removeTransactionById(transDebit.getIdentifier());

        arrTransaction = userOne.getTransactions().toArray();

        System.out.println("\nGetting all user transactions (UserOne) after deleting");

        for (int i = 0; i < arrTransaction.length; i++) {
            System.out.println("-> Transaction IDs: " + arrTransaction[i].getIdentifier());
        }

        arrTransaction = userTwo.getTransactions().toArray();

        System.out.println("\nGetting all user transactions (UserTwo) after deleting");

        for (int i = 0; i < arrTransaction.length; i++) {
            System.out.println("-> Transaction IDs: " + arrTransaction[i].getIdentifier());
        }

        System.out.println("------------------------------------------");

        System.out.println("\nAttempt to delete a non-existent transaction");

        UUID id = UUID.randomUUID();
        userOne.getTransactions().removeTransactionById(id);

    }
}
