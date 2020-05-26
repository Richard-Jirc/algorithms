public class InsertSortMyVer {

    public static void inSort(int[] list, int N) {
        for (int i = N; i < list.length; i++) {
            int j = i;
            while ((j >= N) && (list[j - N] > list[j])) {
                int man = list[j - N];
                list[j - N] = list[j];
                list[j] = man;
                j -= N;
            }
        }
    }

    public static void print(int[] list) {
        System.out.print("\n");
        for (int i = 0; i < list.length; i++) {
            if (i < 10) System.out.print("_" + i + "| ");
            else System.out.print(i + "| ");
        }
        System.out.print("\n");
        for (int a : list) {
            if (a < 10) System.out.print("0" + a + ", ");
            else System.out.print(a + ", ");
        }
        System.out.print("\n");
    }

    public static void printPart(int[] list, int low, int end) {
        for (int i = low; i <= end; i++) {
            System.out.print(list[i] + ", ");
        }
        System.out.print("\n");
    }

    public static int[] randomGenerator(int N) {
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = (int) (Math.random() * 100);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] list = randomGenerator(20);
        print(list);
        inSort(list, 2);
        print(list);
    }
}
