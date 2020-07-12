import java.util.*;

public class StaticAlgorithms {


    public static int reverseInteger(int x) {
        boolean minus = x < 0;
        int y = Math.abs(x);

        LinkedList<Integer> queue = new LinkedList<>();
        int result = 0;
        while (y != 0) {
            int k = y % 10;
            result = result * 10 + k;
            y = (y - k) / 10;
        }
        if (minus) result *= -1;
        return result;
    }

    public static void main(String[] args) {
        System.out.print(reverseInteger(15342));
    }

}
