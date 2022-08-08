package d01.ex04;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private int transferAmount;

    enum Category{
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, Category transferCategory, int transferAmount){
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;

        if (transferCategory == Category.DEBIT && transferAmount < 0){
            System.out.println("Warning: Debit cannot be negative. Set to 0 by default");
            this.transferAmount = 0;
        }else if (transferCategory == Category.CREDIT && transferAmount > 0){
            System.out.println("Warning: Credit cannot be positive. Set to 0 by default");
            this.transferAmount = 0;
        }else{
            this.transferAmount = transferAmount;
        }
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public void setTransferCategory(Category transferCategory) {
        this.transferCategory = transferCategory;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        if (transferCategory == Category.DEBIT && transferAmount < 0){
            System.out.println("Warning: Debit cannot be negative. Set to 0 by default");
            this.transferAmount = 0;
        }else if (transferCategory == Category.CREDIT && transferAmount > 0){
            System.out.println("Warning: Credit cannot be positive. Set to 0 by default");
            this.transferAmount = 0;
        }else{
            this.transferAmount = transferAmount;
        }
    }

}
