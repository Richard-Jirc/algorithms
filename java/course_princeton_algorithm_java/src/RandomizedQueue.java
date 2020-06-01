import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "ManualArrayCopy"})
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] list;
    private int pointer;

    public RandomizedQueue() {
        list = (Item[]) new Object[4];
        pointer = 0;
    }
    public boolean isEmpty() {
        return pointer == 0;
    }
    public int size() {
        return pointer;
    }
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (pointer == list.length) list = resize(list.length * 2);
        list[pointer++] = item;
    }
    public Item dequeue() { // should be returning a random item.
        if (this.isEmpty()) throw new NoSuchElementException();
        if (list.length > 4 && pointer <= list.length / 4) list = resize(list.length / 2);
        int index = StdRandom.uniform(pointer);
        Item randomItem = swap(index, pointer - 1);
        list[--pointer] = null;
        return randomItem;
    }
    public Item sample() { // return a random item without removing it.
        if (this.isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(pointer);
        return list[index];
    }

    private Item[] resize(int length) {
        Item[] newList = (Item[]) new Object[length];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        return newList;
    }
    private Item swap(int selected, int tail) {
        Item middle = list[selected];
        list[selected] = list[tail];
        list[tail] = middle;
        return middle;
    }

    public Iterator<Item> iterator() { return new RandomQueueIterator(); }
    private class RandomQueueIterator implements Iterator<Item> { // in a random order.
        int index = pointer;
        @Override
        public boolean hasNext() {
            return index > 0;
        }
        @Override
        public Item next() {
            int pick = StdRandom.uniform(index);
            swap(pick, index - 1);
            return list[--index];
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(3);
        test.enqueue(4);
        test.enqueue(5);
        test.enqueue(6);
        test.enqueue(7);
        StdOut.println(test.sample());
        StdOut.println(test.dequeue());
        for (Integer i : test) {
            StdOut.print(i + ",");
        }
    }
}
