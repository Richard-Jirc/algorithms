import java.util.*;

public class StaticAlgorithms {
    
    /**Reverse Integer: last modified - 20.7.12
     * @param x int to reverse
     * @return 0 if the result {@code OVERFLOWS}, else the reversed Integer.
     */
    public static int reverseInteger(int x) {
        boolean minus = x < 0;
        int y = Math.abs(x);
        long result = 0;
        while (y != 0) {
            int k = y % 10;
            result = result * 10 + k;
            y = (y - k) / 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0; // TODO: OVERFLOW? HUH?
        if (minus) result *= -1;
        return (int) result;
    }

    /**TwoSum SORTED: last modified - 20.7.12
     * if not sorted, the HashMap solution maybe favorable.
     * but since is sorted...
     */
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) break;
            else if (numbers[j] > target - numbers[i]) j--;
            else i++;
        }
        return new int[]{i + 1, j + 1};
    }

    public static void main(String[] args) {
        int[] test = {2, 7, 11, 18};
        System.out.print(Arrays.toString(twoSum(test, 9)));
    }

}
