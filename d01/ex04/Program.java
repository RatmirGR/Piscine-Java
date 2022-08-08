package d01.ex04;

import java.util.UUID;

public class Program {



    public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException{

        System.out.println("User creation");
        User userOne = new User("Tom", 150);
        User userTwo = new User("Tim", 700);

        System.out.println("\nReceiving balance befor transactions");
        System.out.println("Name: " + userOne.getName() + ", balance: " + userOne.getBalance());
        System.out.println("Name: " + userTwo.getName() + ", balance: " + userTwo.getBalance());

        System.out.println("\nConducting transactions");
        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser(userOne);
        transactionsService.addUser(userTwo);
        transactionsService.transferTransaction(userOne.getIdentifier(), userTwo.getIdentifier(), 100);
        transactionsService.transferTransaction(userOne.getIdentifier(), userTwo.getIdentifier(), 200);
        transactionsService.transferTransaction(userOne.getIdentifier(), userTwo.getIdentifier(), 150);

        System.out.println("\nReceiving balance after transactions");
        System.out.println("Name: " + userOne.getName() + ", balance: " + transactionsService.retrieveUserBalance(userOne.getIdentifier()));
        System.out.println("Name: " + userTwo.getName() + ", balance: " + transactionsService.retrieveUserBalance(userTwo.getIdentifier()));


        System.out.println("\nDeleting a transaction");
        Transaction[] arrUserTrans = transactionsService.retrieveTransfers(userOne.getIdentifier());
        UUID transId = arrUserTrans[0].getIdentifier();
        transactionsService.removeTransaction(transId, userOne.getIdentifier());

        System.out.println("\nReceiving unpaired transactions");
        Transaction[] arrUnpairTrans = transactionsService.transactionVerification();
        for (int i = 0; i < arrUnpairTrans.length; i++) {
            System.out.println(arrUnpairTrans[i].getIdentifier());
        }

        System.out.println("\nDeleting the second pair of transactions");
        transactionsService.removeTransaction(arrUnpairTrans[0].getIdentifier(), userTwo.getIdentifier());

        System.out.println("\nReceiving unpaired transactions");
        arrUnpairTrans = transactionsService.transactionVerification();
        for (int i = 0; i < arrUnpairTrans.length; i++) {
            System.out.println(arrUnpairTrans[i].getIdentifier());
        }

        System.out.println("----------------------------------");

        System.out.println("\nAttempt to enter an amount greater than the balance amount");
        transactionsService.transferTransaction(userOne.getIdentifier(), userTwo.getIdentifier(), 251);
    }
}
