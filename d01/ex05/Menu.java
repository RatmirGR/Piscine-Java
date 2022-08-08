package d01.ex05;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private Scanner scanner;
    private TransactionsService transService;
    private final Mode mode;
    private int command;

    private static final int CMD_ADD_USER = 1;
    private static final int CMD_VIEW_USER_BALANCE = 2;
    private static final int CMD_PERFORM_TRANSFER = 3;
    private static final int CMD_VIEW_ALL_TRANS_USER = 4;
    private static final int CMD_DEV_REMOVE_TRANS_BY_ID = 5;
    private static final int CMD_DEV_CHECK_TRANS_VALIDITY = 6;
    private final int CMD_FINISH_EXECUTION;
    private static final int MAX_COUNT_ERRORS = 3;

    enum Mode{
        PRODUCTION,
        DEV
    }

    public Menu(TransactionsService service, Mode mode){
        transService = service;
        scanner = new Scanner(System.in);
        this.mode = mode;

        if (mode == Mode.DEV){
            CMD_FINISH_EXECUTION = 7;
        }else{
            CMD_FINISH_EXECUTION = 5;
        }
    }

    private boolean isValidateCommand(Scanner scanner){
        String input;
        boolean flag = true;

        try {
            input = scanner.nextLine();
            try {
                command = Integer.parseInt(input);
                if (command < CMD_ADD_USER || command > CMD_FINISH_EXECUTION){
                    System.out.println("Warning: Command not found. Please enter the correct command");
                    flag = false;
                }
            }catch (NumberFormatException ex){
                System.out.println("Warning: Invalid command. Please enter the correct command");
                flag = false;
            }
        }catch (NoSuchElementException e){
            System.out.println("Warning: Invalid or empty command. Please enter the correct command");
            flag = false;
        }
        return true;
    }

    public void launch(){

        while (true){
            command = 0;
            printCommand();

            if (!isValidateCommand(scanner)){
                continue;
            }
            switch (command){
                case CMD_ADD_USER:
                    addUser();
                    break;
                case CMD_VIEW_USER_BALANCE:
                    viewUserBalance();
                    break;
                case CMD_PERFORM_TRANSFER:
                    performTransfer();
                    break;
                case CMD_VIEW_ALL_TRANS_USER:
                    viewAllTransUser();
                    break;
                case CMD_DEV_REMOVE_TRANS_BY_ID:
                    if (mode == Mode.DEV) {
                        removeTransferById();
                    }else{
                        return;
                    }
                    break;
                case CMD_DEV_CHECK_TRANS_VALIDITY:
                    checkTransValidity();
                    break;
                default:
                    return;
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    private void addUser(){
        String name;
        int balance;
        String[] arrInput;
        int countErrors = 0;

        System.out.println("Enter a user name and a balance");
        while (true) {
            try {
                arrInput = scanner.nextLine().split("\\s+");

                if (arrInput.length == 0 || arrInput[0].length() == 0){
                    System.out.println("Warning: Empty name. Please enter the correct name");
                }else if (arrInput.length == 1){
                    System.out.println("Warning: Empty balance. Please enter the correct balance");
                }else if (arrInput.length > 2){
                    System.out.println("Warning: Too many arguments. Please enter a valid name and balance");
                }else {

                    try {
                        name = arrInput[0];
                        balance = Integer.parseInt(arrInput[1]);
                        User user = new User(name, balance);
                        transService.addUser(user);
                        System.out.printf("User with id = %d is added\n", user.getIdentifier());
                        break;
                    }catch (NumberFormatException ex) {
                        System.out.println("Warning: Invalid balance. Please enter the correct balance");
                    }
                }
            }catch (NoSuchElementException e){
                System.out.println("Warning: Invalid or empty input. Please enter the correct input");
            }
            countErrors++;
            if (countErrors == MAX_COUNT_ERRORS){
                break;
            }
        }
    }

    private void viewUserBalance(){
        String input, name;
        int userId, balance;
        int countErrors = 0;

        System.out.println("Enter a user ID");
        while (true) {
            try {
                input = scanner.nextLine();
                try {
                    userId = Integer.parseInt(input);
                    try {
                        name = transService.retrieveUserById(userId);
                        balance = transService.retrieveUserBalance(userId);
                        System.out.printf("%s - %d\n", name, balance);
                        break;
                    }catch (UserNotFoundException ex){
                        System.out.println("User is not found. Please enter a valid ID");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Warning: Invalid id. Please enter the correct id");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Warning: Invalid or empty input. Please enter the correct input");
            }
            countErrors++;
            if (countErrors == MAX_COUNT_ERRORS){
                break;
            }
        }
    }

    private void performTransfer(){
        int senderId, recipientId, transferAmount;
        String[] arrInput;
        int countErrors = 0;

        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        while (true) {
            try {
                arrInput = scanner.nextLine().split("\\s+");

                if (arrInput.length == 0 || arrInput[0].length() == 0){
                    System.out.println("Warning: Empty sender ID. Please enter the correct sender ID");
                }else if (arrInput.length == 1) {
                    System.out.println("Warning: Empty recipient ID. Please enter the correct recipient ID");
                }else if (arrInput.length == 2){
                    System.out.println("Warning: Empty transfer amount. Please enter the correct transfer amount");
                }else if (arrInput.length > 3){
                    System.out.println("Warning: Too many arguments. Please enter a valid sender ID, a recipient ID, and a transfer amount");
                }else {

                    try {
                        senderId = Integer.parseInt(arrInput[0]);
                        recipientId = Integer.parseInt(arrInput[1]);
                        transferAmount = Integer.parseInt(arrInput[2]);
                        try {
                            transService.transferTransaction(recipientId, senderId, transferAmount);
                            System.out.println("The transfer is completed");
                            break;
                        }catch (UserNotFoundException ex){
                            System.out.println("Warning: User not found. Please enter the correct input");
                        }catch (IllegalTransactionException ex){
                            System.out.println("Warning: Illegal Transaction. Please enter the correct input");
                        }
                    }catch (NumberFormatException ex) {
                        System.out.println("Warning: Invalid input. Please enter the correct input");
                    }
                }
            }catch (NoSuchElementException e){
                System.out.println("Warning: Invalid or empty input. Please enter the correct input");
            }
            countErrors++;
            if (countErrors == MAX_COUNT_ERRORS){
                break;
            }
        }

    }

    private void viewAllTransUser(){
        String input;
        int userId;
        Transaction[] arr;
        int countErrors = 0;

        System.out.println("Enter a user ID");
        while (true) {
            try {
                input = scanner.nextLine();
                try {
                    userId = Integer.parseInt(input);
                    try {
                        arr = transService.retrieveTransfers(userId);
                        for (int i = 0; i < arr.length; i++) {
                            User user = arr[i].getSender();
                            System.out.printf("To %s(id = %d) %d with id = %s\n",
                                    user.getName(), user.getIdentifier(), arr[i].getTransferAmount(), arr[i].getIdentifier());
                        }
                        break;
                    }catch (UserNotFoundException ex){
                        System.out.println("User is not found. Please enter a valid ID");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Warning: Invalid id. Please enter the correct id");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Warning: Invalid or empty input. Please enter the correct input");
            }
            countErrors++;
            if (countErrors == MAX_COUNT_ERRORS){
                break;
            }
        }
    }

    private void removeTransferById(){
        int userId;
        String transferId;
        String[] arrInput;
        Transaction remoteTransUser;
        int countErrors = 0;

        System.out.println("Enter a user ID and a transfer ID");
        while (true) {
            try {
                arrInput = scanner.nextLine().split("\\s+");

                if (arrInput.length == 0 || arrInput[0].length() == 0){
                    System.out.println("Warning: Empty user ID . Please enter the correct user ID ");
                }else if (arrInput.length == 1){
                    System.out.println("Warning: Empty transfer ID. Please enter the correct transfer ID");
                }else if (arrInput.length > 2){
                    System.out.println("Warning: Too many arguments. Please enter a valid user ID and transfer ID");
                }else {

                    try {
                        userId = Integer.parseInt(arrInput[0]);
                        transferId = arrInput[1];
                        remoteTransUser = transService.removeTransaction(UUID.fromString(transferId), userId);
                        User user = remoteTransUser.getSender();
                        System.out.printf("Transfer To %s(id = %d) %d removed\n",
                                user.getName(), user.getIdentifier(), -remoteTransUser.getTransferAmount());
                        break;
                    }catch (NumberFormatException ex) {
                        System.out.println("Warning: Invalid balance. Please enter the correct balance");
                    }
                }
            }catch (NoSuchElementException e){
                System.out.println("Warning: Invalid or empty input. Please enter the correct input");
            }
            countErrors++;
            if (countErrors == MAX_COUNT_ERRORS){
                break;
            }
        }
    }

    private void checkTransValidity(){
        User recipient, sender, userOne, userTwo;
        Transaction[] arrTrans = transService.transactionVerification();

        System.out.println("Check results:");
        for (int i = 0; i < arrTrans.length; i++) {
            recipient = arrTrans[i].getRecipient();
            sender = arrTrans[i].getSender();
            if (recipient.getTransactions().isTransIdInTransList(arrTrans[i].getIdentifier())){
                userOne = recipient;
                userTwo = sender;
            }else{
                userOne = sender;
                userTwo = recipient;
            }
            System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
                    userOne.getName(), userOne.getIdentifier(),
                    arrTrans[i].getIdentifier(),
                    userTwo.getName(), userTwo.getIdentifier(),
                    arrTrans[i].getTransferAmount());
        }
    }

    private void printCommand(){
        int codeCmd = 5;

        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (this.mode == Mode.DEV){
            codeCmd = 7;
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }
        System.out.println(codeCmd + ". Finish execution");
    }
}
