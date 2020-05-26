/*
 * W2 Assignment: Deque Class
 *
 *
 * */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int tail;
    private int size;
    private Item[] list;

    public RandomizedQueue() {

        list = (Item[]) new Object[4];
        tail = 0;
        size = 0;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enQueue(Item item) {
        if (tail == list.length) resize(2 * list.length);
        list[tail++] = item;
        size++;
    }

    public Item deQueue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("deQueue error");
        int pick = (int) (Math.random() * tail);
        Item tailValue = list[tail - 1];
        Item deQueValue = list[pick];
        list[tail - 1] = null;
        list[pick] = tailValue;
        size--;
        return deQueValue;
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("sample error");
        int pick = (int) (Math.random() * tail);
        return list[pick];
    }

    // Cleans deQueued holes
    private void resize(int cap) {
        Item[] copy = (Item[]) new Object[cap];
        for (int i = 0; i < tail; i++) {
            if (list[i] == null) continue;
            copy[i] = list[i];
        }
        list = copy;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = tail;

        public boolean hasNext() {
            if (i == 1) return list[0] != null;
            else return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove error");
        }

        public Item next() {
            if (!(hasNext())) throw new java.util.NoSuchElementException("next error");
            do {
                i--;
            } while (list[i] == null);
            return list[i];
        }
    }

    private void queuePrinter() {
        System.out.print("\n");
        if (this.isEmpty()) System.out.print("Empty!");
        for (Object s : this) {
            System.out.print(s + ", ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enQueue(200);
        q.enQueue(300);
        q.enQueue(400);

        q.queuePrinter();

        q.enQueue(500);
        q.enQueue(60);
        q.enQueue(700);
        q.enQueue(80);
        q.enQueue(9);
        int ha = q.deQueue();

        System.out.println(ha);
        q.queuePrinter();

    }

}



