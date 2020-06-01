import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class StackGenericArray<Item> implements Iterable<Item> {

    Item[] list;
    int pointer;

    public StackGenericArray() {
        list = (Item[]) new Object[4];
        pointer = 0;
    }
    public void push(Item item) {
        if (pointer >= list.length) this.resize(list.length * 2);
        list[pointer] = item;
        pointer++;
        StdOut.println(list.length);
    }
    public Item pop() {
        if (this.isEmpty()) throw new IllegalArgumentException();
        Item item = list[pointer - 1];
        list[pointer - 1] = null;
        pointer--;
        if (list.length > 4 && pointer <= list.length / 4) this.resize(list.length / 2);
        return item;
    }
    public boolean isEmpty() {
        return pointer == 0;
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    private class StackIterator implements Iterator<Item> {
        int where = 0;
        @Override
        public boolean hasNext() {
            return where < pointer;
        }

        @Override
        public Item next() {
            while (list[pointer] == null) {
                pointer++;
            }
            return list[where++];
        }
    }

    private void resize(int length) {
        Item[] newArray = (Item[]) new Object[length];
        for (int i = 0; i < length; i++) {
            if (list[i] == null) continue;
            newArray[i] = list[i];
        }
        StdOut.print(newArray.length);
        list = newArray;
    }

    public static void main(String[] args) {
        StackGenericArray<Integer> test = new StackGenericArray<Integer>();
        test.push(3);
        test.push(4);
        test.push(5);
        test.push(6);
        for (Integer i: test) {
            StdOut.print(i + ",");
        }
    }
}
