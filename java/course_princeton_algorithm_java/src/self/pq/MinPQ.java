package self.pq;
import edu.princeton.cs.algs4.StdDraw;

/** MinPQ:
 *  keep the SMALLEST item at the root of the binary heap.
 * */
public class MinPQ<T extends Comparable<T>> {

    private final T[] list;
    private int size;


    //  -,"1","2",   "4",            "8",         ...
    // [0, 1, 2,3, 4,5,6,7, 8,9,10,11,12,13,14,15 ...]
    public MinPQ() {
        list = (T[]) new Comparable[8];
        size = 1;
    }

    /**INSERT api:
     * insert an element then perform SWIM*/
    public void insert(T item) {
        list[size] = item;
        swim(size);
        size++;
    }
    public int size() {
        return size - 1;
    }
    public boolean isEmpty() {
        return size == 1;
    }
    public T delMin() {
        swap(1, size - 1);
        sink(1);
        T minValue = list[--size];
        list[size] = null;
        return minValue;
    }

    /**LESS helper function:
     * if true, a < b.*/
    private boolean less(int a, int b) {
        return list[a].compareTo(list[b]) <= 0;
    }
    private void swap(int a, int b) {
        T mid = list[b];
        list[b] = list[a];
        list[a] = mid;
    }

    /**SINK helper function: if any child is smaller than self, recursively swap. */
    private void sink(int index) {
        while (index * 2 + 1 < size) {
            int smaller = index * 2;
            if (less(index * 2 + 1, index)) smaller++;
            if (less(index, smaller)) break;
            swap(index, smaller);
            index = smaller;
        }
    }

    /**SWIM helper function: if self is smaller than parent, recursively swap.*/
    private void swim(int index) {
        while (less(index, index / 2) && index > 1) {
            swap(index, index / 2);
            index /= 2;
        }
    }



    public static void main(String[] args) {

    }
}
