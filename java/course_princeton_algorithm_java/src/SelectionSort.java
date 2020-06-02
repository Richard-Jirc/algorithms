import java.util.Comparator;

public class SelectionSort<T> {

    int numberOfCompare;

    public SelectionSort() {
        numberOfCompare = 0;
    }

    public void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[j], list[smallest]) <= 0) smallest = j;
                numberOfCompare++;
            }
            swap(list, i, smallest);
        }
    }
    private void swap(T[] list, int a, int b) {
        if (a == b) return;
        T middle = list[b];
        list[b] = list[a];
        list[a] = middle;
    }

    public static void main(String[] args) {
        Integer[] test = {2, 6, 9, 10, 2, 3, 20, 68, 1, 43, 20, 30};
        int value = 7;
        for (int i : test) {
            System.out.print(i + ",");
        }
        System.out.println();
        SelectionSort<Integer> Sort = new SelectionSort<Integer>();
        Sort.sort(test, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Sort.numberOfCompare); // ~ o(n^2): still too slow
        for (int i : test) {
            System.out.print(i + ",");
        }

    }
}
