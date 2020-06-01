public class QueueByGenericLL<Item> {

    Node first;
    Node last;

    public QueueByGenericLL() {
    }
    private class Node {
        Item item;
        Node next;
    }
    public void enqueue(Item value) {
        Node oldLast = last;
        last = new Node();
        last.item = value;
        if (this.isEmpty()) first = last;
        else oldLast.next = last;
    }
    public Item dequeue() {
        if (this.isEmpty()) throw new IllegalArgumentException();
        Item removed = first.item;
        first = first.next;
        if (this.isEmpty()) last = null;
        return removed;
    }
    public boolean isEmpty() { return first == null; }


    public static void main(String[] args) {

    }
}
