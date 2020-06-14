package self.pq;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class MinPQ<T extends Comparable<T>> {

    private final T[] list;
    private int size;

    //  -,"1","2",   "4",            "8",         ...
    // [0, 1, 2,3, 4,5,6,7, 8,9,10,11,12,13,14,15 ...]
    public MinPQ() {
        list = (T[]) new Comparable[8];
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
        while (list[index / 2].compareTo(list[index]) >= 0 && index > 1) {
            swap(index, index / 2);
            index /= 2;
        }
    }
    private void swap(int a, int b) {
        T mid = list[b];
        list[b] = list[a];
        list[a] = mid;
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.point(1, 1);
    }
}
