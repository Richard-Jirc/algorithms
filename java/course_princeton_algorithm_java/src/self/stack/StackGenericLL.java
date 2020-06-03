package self.stack;

public class StackGenericLL {

    Node first;

    public StackGenericLL() {
        first = null;
    }
    private static class Node {
        String item;
        Node next;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }
    public String pop() {
        Node oldFirst = first;
        first = first.next;
        return oldFirst.item;
    }
    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {

    }
}
