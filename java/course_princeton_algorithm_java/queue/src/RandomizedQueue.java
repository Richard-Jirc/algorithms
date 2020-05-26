import java.util.Iterator;

@SuppressWarnings("WeakerAccess")
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int tail;
    private int size;
    private Item[] list;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        tail = 0;
        size = 0;
        list = (Item[]) new Object[4];
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

    public Iterator<Item> iterator() {
        return new RevArrayIterator();
    }

    private class RevArrayIterator implements Iterator<Item> {
        private int i = tail;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("next error");
        }

        public Item next() {
            i--;
            return list[i];
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        Item[] copy = (Item[]) new Object[cap];
        for (int i = 0; i < tail; i++) {
            if (list[i] == null) continue;
            copy[i] = list[i];
        }
        list = copy;
    }

    public static void main(String[] args) {

    }
}
