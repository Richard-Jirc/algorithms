import java.util.Comparator;

public class SelectionSort<T> {

    public void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[j], list[i]) < 0) smallest = j;
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
        Integer[] test = {2, 6, 9, 10, 2};
        int value = 7;
        for (int i : test) {
            System.out.print(i + ",");
        }
        SelectionSort<Integer> Sort = new SelectionSort<Integer>();
        Sort.sort(test, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println();
        for (int i : test) {
            System.out.print(i + ",");
        }

    }
}
