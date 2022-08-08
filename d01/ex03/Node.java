package d01.ex03;

public class Node {
    private final Transaction value;
    private Node next;
    private Node previous;

    public Node(Transaction value){
        this.value = value;
    }

    public Transaction getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}