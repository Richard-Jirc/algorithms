import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "ManualArrayCopy"})
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] list;
    private int pointer, deleted;

    public RandomizedQueue() {
        list = (Item[]) new Object[4];
        pointer = 0;
        deleted = 0;
    }
    public boolean isEmpty() {
        return pointer == 0;
    }
    public int size() {
        return pointer - deleted;
    }
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (pointer == list.length) list = resize(list.length * 2);
        list[pointer] = item;
        pointer++;
    }
    public void dequeue() { // should be returning a random item.
        if (this.isEmpty()) throw new NoSuchElementException();

    }
    public void sample() { // return a random item without removing it.
        if (this.isEmpty()) throw new NoSuchElementException();

    }

    private Item[] resize(int length) {
        Item[] newList = (Item[]) new Object[length];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        return newList;
    }

    public Iterator<Item> iterator() { return new RandomQueueIterator(); }
    private class RandomQueueIterator implements Iterator<Item> { // in a random order.
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < pointer;
        }
        @Override
        public Item next() {
            while (list[index] == null) {
                index++;
            }
            return list[index++];
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(3);
        test.enqueue(5);
        for (Integer i : test) {
            StdOut.print(i + ",");
        }
    }
}
