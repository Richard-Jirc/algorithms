public class bUpMergeSortMyVer {

    public static void sort(int[] list) {
        int N = list.length;
        int[] Dup = new int[N];
        for (int sz = 1; sz < N; sz *= 2) {
            for (int low = 0; low < N - sz; low += sz + sz) {
                MergeMyVer.merge(list, Dup, low, (low + sz - 1), Math.min((low + sz * 2 - 1), (N - 1)));
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
