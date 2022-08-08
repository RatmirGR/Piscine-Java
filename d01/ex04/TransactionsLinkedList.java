package d01.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{

    private Node first;
    private Node last;
    private int size;

    public TransactionsLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);

        if (first == null){
            first = newNode;
        }else{
            last.setNext(newNode);
            newNode.setPrevious(last);
        }
        last = newNode;
        size++;
    }

    @Override
    public Transaction removeTransactionById(UUID id) throws TransactionNotFoundException {
        Node current = first;

        while (current != null){
            if (current.getValue().getIdentifier().equals(id)){
                if (current == first){
                    first = current.getNext();
                }
                if (current.getPrevious() != null){
                    current.getPrevious().setNext(current.getNext());
                }
                if (current.getNext() != null){
                    current.getNext().setPrevious(current.getPrevious());
                }
                size--;
                return current.getValue();
            }
            current = current.getNext();
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] arrTrans = new Transaction[size];
        Node current = first;

        for (int i = 0; i < size; i++) {
            arrTrans[i] = current.getValue();
            current = current.getNext();
        }
        return arrTrans;
    }

    @Override
    public Transaction retrieveTransactionById(UUID id) throws TransactionNotFoundException {
        Node current = first;

        while (current != null){
            if (current.getValue().getIdentifier().equals(id)){
                return current.getValue();
            }
            current = current.getNext();
        }
        throw new TransactionNotFoundException();
    }

    public boolean isTransIdInTransList(UUID id) throws TransactionNotFoundException {
        Node current = first;

        while (current != null){
            if (current.getValue().getIdentifier().equals(id)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
