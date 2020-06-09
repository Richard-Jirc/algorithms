package self.pq;

public class MinPQ<T extends Comparable<T>> {

    private T[] list;
    private int size;

    public MinPQ() {
        list = (T[]) new Object[7];
    }
    public void insert(T item) {
        list[size] = item;
        swim(size);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void sink(int index) {

    }
    private void swim(int index) {

    }

    public static void main(String[] args) {

    }
}
