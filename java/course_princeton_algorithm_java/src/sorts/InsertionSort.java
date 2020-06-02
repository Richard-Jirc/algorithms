package sorts;

import java.util.Comparator;

public class InsertionSort<T> {

    int swapping;
    int comparison;

    public InsertionSort() {
        swapping = 0;
        comparison = 0;
    }

    public void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length; i++) {
            if (i == 0) continue;
            for (int j = i; j > 0; j--) {
                comparison++;
                if (comparator.compare(list[j], list[j - 1]) < 0) swap(list, j, j - 1);
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
        Integer[] array = {1, 2, 3, 4, 5, 6};
        InsertionSort<Integer> sort = new InsertionSort<>();
        sort.sort(array, new Comparator<Integer>() {
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
