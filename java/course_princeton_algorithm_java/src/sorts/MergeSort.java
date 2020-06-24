package sorts;

import java.util.Comparator;

public class MergeSort<T> {

    private final Comparator<T> comparator;
    private T[] aux;

    public MergeSort(Comparator<T> input) {
        comparator = input;
    }
    public void sort(T[] array) {
        aux = (T[]) new Object[array.length];
        recursive(array, 0, array.length - 1);
    }

    private void recursive(T[] array, int lo, int hi) {
        if (lo == hi) {
            System.out.println("Root: " + lo);
        }
        else {
            int mid = (lo + hi + 1) / 2;
            recursive(array, lo, mid - 1);
            recursive(array, mid, hi);
            merge(array, lo, mid, hi);
        }
    }
    private void merge(T[] original, int lo, int mid, int hi) {
        for (int a = lo; a <= hi; a++) {
            aux[a] = original[a];
        }
        System.out.println("Merging " + lo + ", " + mid + ", " + hi);
        int k = lo, i = lo, j = mid;
        while (k <= hi) {
            if (i >= mid) original[k++] = aux[j++];
            else if (j > hi) original[k++] = aux[i++];
            else if (comparator.compare(aux[i], aux[j]) <= 0) original[k++] = aux[i++];
            else original[k++] = aux[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> MSort = new MergeSort<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Integer[] hey = {-50, 100, -60, 200, -70, 150};
        MSort.sort(hey);
        for (Integer i : hey) {
            System.out.print(i + ",");
        }
    }
}
