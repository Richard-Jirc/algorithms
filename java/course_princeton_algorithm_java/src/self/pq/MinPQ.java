package self.pq;

public class MinPQ<T extends Comparable<T>> {

    private T[] list;
    private int size;

    public MinPQ() {
        list = (T[]) new Object[7];
    }
    public void insert(T item) {
        list[size] = item;

    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void sink(T item) {

    }
    private void swim(T item) {

    }

    public static void main(String[] args) {

    }
}
