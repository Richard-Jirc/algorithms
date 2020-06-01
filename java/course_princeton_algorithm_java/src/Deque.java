
import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque by double linked-list

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;

    public Deque() {
        first = null;
        last = null;
    }
    private class Node {
        Node prev;
        Item value;
        Node next;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    public void addFirst(Item value) {
        if (value == null) throw new IllegalArgumentException();
        size++;
        Node oldFirst = first;
        first = new Node();
        first.value = value;
        first.next = oldFirst;
        if (oldFirst == null) last = first;
        else oldFirst.prev = first;
    }
    public void addLast(Item value) {
        if (value == null) throw new IllegalArgumentException();
        size++;
        Node oldLast = last;
        last = new Node();
        last.value = value;
        last.prev = oldLast;
        if (oldLast == null) first = last;
        else oldLast.next = last;
    }
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();
        size--;
        Node oldFirst = first;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        return oldFirst.value;
    }
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException();
        size--;
        Node oldLast = last;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        return oldLast.value;
    }

    public Iterator<Item> iterator() { return new DequeIterator(); }
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.value;
            current = current.next;
            return item;
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }
    public static void main(String[] args) {
//        Deque<Integer> test = new Deque<Integer>();
//        test.addFirst(1);
//        test.addLast(2);
//        test.addLast(3);
//        test.addFirst(0);
//        test.removeFirst();

//        if (test.first != null) {
//            StdOut.println(test.first);
//            StdOut.println("First.value:" + test.first.value);
//            StdOut.println("First.prev:" + test.first.prev);
//            StdOut.println("First.next:" + test.first.next);
//        } else {
//            StdOut.println("First is null");
//        }
//        if (test.last != null) {
//            StdOut.println(test.last);
//            StdOut.println("Last.value:" + test.last.value);
//            StdOut.println("Last.prev:" + test.last.prev);
//            StdOut.println("Last.next:" + test.last.next);
//        } else {
//            StdOut.println("last is null");
//        }
//        for (Integer i: test) {
//            StdOut.print(i + ",");
//        }
//        StdOut.println("size:" + test.size());
    }
}
