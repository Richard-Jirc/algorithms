package sorts;

import java.util.Comparator;

public class InsertionSort<T> {

    int swapping;
    int comparison;

    public void sort(T[] list, int step, Comparator<T> comparator) {
        swapping = 0;
        comparison = 0;
        for (int i = 1; i < list.length; i++) {
            for (int j = i; j >= step; j--) { // throw smallest item to the head
                comparison++;
                if (comparator.compare(list[j], list[j - step]) < 0) swap(list, j, j - step);
                else break;
            }
        }
    }
    private void swap(T[] list, int a, int b) {
        if (a == b) return;
        T middle = list[b];
        list[b] = list[a];
        list[a] = middle;
        swapping++;
    }

    public static void main(String[] args) {
        Integer[] array = {5, 4, 3, 2, 1, 0};
        InsertionSort<Integer> sort = new InsertionSort<>();
        sort.sort(array, 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("Swapped:" + sort.swapping); // o(n^2) worst-case, constant best-case
        System.out.println("Compare:" + sort.comparison); // o(n^2) worst-case, o(n) best-case
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

}
