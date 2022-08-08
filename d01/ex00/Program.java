package d01.ex00;

public class Program {

    public static void main(String[] args) {

        System.out.println("\nExample of creating a user named John and with a balance of 10");
        User userOne = new User(1, "John", 10);

        System.out.println("-> Identifier: " + userOne.getIdentifier() + ", User: " + userOne.getName() +
                            ", Balance: " + userOne.getBalance());

        System.out.println("\nExample of creating a user named Mike and with a balance of 200");
        User userTwo = new User(4, "Mike", 200);

        System.out.println("-> Identifier: " + userTwo.getIdentifier() +
                ", User: " + userTwo.getName() +
                ", Balance: " + userTwo.getBalance());

        System.out.println("-------------------------------------------------");

        System.out.println("\nTransaction example");

        Transaction debit = new Transaction(userOne, userTwo, Transaction.Category.DEBIT, 25);
        Transaction credit = new Transaction(userTwo, userOne, Transaction.Category.CREDIT, -25);
        System.out.println("-> Transfer amount from Mike: " + credit.getTransferAmount());
        System.out.println("-> Transfer amount to John: " + debit.getTransferAmount());
        userOne.setBalance(userOne.getBalance() + debit.getTransferAmount());
        userTwo.setBalance(userTwo.getBalance() + credit.getTransferAmount());

        System.out.println("-------------------------------------------------");

        System.out.println("\nBalance after transactions");
        System.out.println("-> Balance Mike: " + userTwo.getBalance());
        System.out.println("-> Balance John: " + userOne.getBalance());

        System.out.println("\n=================================================");

        System.out.println("\nExample of creating a user with a negative balance (-20)");
        User userZero = new User(8, "Tom", -20);

        System.out.println("-> Identifier: " + userZero.getIdentifier() + ", User: " + userZero.getName() +
                ", Balance: " + userZero.getBalance());

        System.out.println("-------------------------------------------------");

        System.out.println("\nAn example of a transaction with an incorrect amount");

        Transaction debitError = new Transaction(userOne, userTwo, Transaction.Category.DEBIT, -5);
        Transaction creditError = new Transaction(userTwo, userOne, Transaction.Category.CREDIT, 5);
        System.out.println("-> Transfer amount from Mike: " + creditError.getTransferAmount());
        System.out.println("-> Transfer amount to John: " + debitError.getTransferAmount());


    }
}
