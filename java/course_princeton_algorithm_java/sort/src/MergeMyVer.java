public class MergeMyVer {

    private static int CAP = 5;


    public static void sort(int[] list) {
        int[] Dup = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            Dup[i] = list[i];
        }
        recSort(list, Dup, 0, list.length - 1);
    }

    private static void recSort(int[] list, int[] Dup, int low, int end) {
        System.out.println("Requesting// " + low + " to " + end);
        if ((end - low + 1) <= CAP) {
            insertSort(list, low, end);
            return;
        }
        if ((end <= low)) return;
        int mid = low + (end - low) / 2;
        recSort(list, Dup, low, mid);
        recSort(list, Dup, mid + 1, end);
        merge(list, Dup, low, mid, end);
        System.out.print("\n");
    }

    private static void insertSort(int[] list, int low, int end) {
        System.out.print("Insertion-Sorting: " + low + " to " + end + " | ");
        for (int i = low; i <= end; i++) {
            int j = i;
            while ((j > low) && (list[j - 1] > list[j])) {
                int swap = list[j - 1];
                list[j - 1] = list[j];
                list[j] = swap;
                j--;
            }
        }
        InsertSortMyVer.printPart(list, low, end);
    }

    public static void merge(int[] list, int[] listDup, int low, int mid, int end) {
        System.out.println("Merging!! " + low + ", " + mid + ", " + end);
        for (int i = low; i <= end; i++) {
            listDup[i] = list[i];
        }
        int j = low;
        int k = mid + 1;
        for (int i = low; i <= end; i++) {
            if (j > mid) {
                list[i] = listDup[k];
                k++;
            } else if (k > end) {
                list[i] = listDup[j];
                j++;
            } else if (listDup[j] <= listDup[k]) {
                list[i] = listDup[j];
                j++;
            } else if (listDup[j] > listDup[k]) {
                list[i] = listDup[k];
                k++;
            }
        }
    }


    public static void main(String[] args) {
        int[] test = InsertSortMyVer.randomGenerator(30);
        InsertSortMyVer.print(test);
        sort(test);
        InsertSortMyVer.print(test);
    }
}
