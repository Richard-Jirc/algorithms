/*
 * W2 Assignment: Deque Class
 *
 *
 * */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int length;

    private class Node {
        Item item;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        length = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return length;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("addFirst error");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (length == 0) last = first;
        length++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("addLast error");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        length++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("removeFirst error");
        Item item = first.item;
        first = first.next;
        length--;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("removeLast error");
        Item item = last.item;
        last = null;
        length--;
        if (isEmpty()) first = last;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove error");
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void queuePrinter() {
        if (this.isEmpty()) System.out.print("Empty!");
        for (Object s : this) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

//        Deque test = new Deque();
//
//        test.addFirst(33);
//
//        test.addFirst(5);
//
//        test.addFirst(100);
//
//        test.addLast(888);
//
//        test.addLast(999);
//
//        test.removeFirst();
//
//        test.queuePrinter();


    }

}
