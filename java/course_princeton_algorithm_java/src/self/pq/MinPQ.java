package self.pq;

public class MinPQ<T extends Comparable<T>> {

    private T[] list;
    private int size;

    // [0, 1,2, 3,4,5,6, 7,8,9,10,11,12,13,14]
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

    private void resize(int grow) {
        
    }

    public static void main(String[] args) {

    }
}
