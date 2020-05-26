import java.util.Arrays;

public class ShellSortMyVer {

    public static void shellSort(int[] list) {

        int N = list.length;

        int factor = 1;
        while ((factor * 3 + 1) <= N) {
            factor = factor * 3 + 1;
        }

        while (factor >= 1) {
            InsertSortMyVer.inSort(list, factor);
            factor = (factor - 1) / 3;
        }
    }

    public static void main(String[] args) {
//        int[] q = InsertSortMyVer.randomGenerator(30);
//        InsertSortMyVer.print(q);
//        shellSort(q);
//        InsertSortMyVer.print(q);

        Point[] test = new Point[5];
        test[0] = new Point(3, 10);
        test[1] = new Point(5, 1);
        test[2] = new Point(100, 30);
        Arrays.sort(test);
        for (Point a : test) {
            System.out.println(a.toString());
        }

    }

}
