import java.util.*;

public class StaticAlgorithms {



    /**Next Greater Element: last modified - 20.7.18
     * find the smallest integer that has same digits but greater than the input n.
     * return -1 if do not exist.
     */
    public static int nextGreaterElement(int n) {
        int input = n;
        // extract digits first
        int[] temp = new int[10];
        int[] digit;
        int k = 0;
        while (n > 0) {
            temp[k] = n % 10;
            n = (n - k) / 10;
            k++;
        }
        digit = new int[k];
        System.arraycopy(temp, 0, digit, 0, k);

        int minDiff = 0;
        for (int j = 0; j < digit.length; j++) {
            for (int s = j + 1; s < digit.length; s++) {
                if (digit[s] >= digit[j]) continue;
                int diff = (int) ((digit[j] - digit[s]) * Math.pow(10, s) + (digit[s] - digit[j]) * Math.pow(10, j));
                if (diff > minDiff && minDiff != 0) break;
                minDiff = diff;
            }
        }

        if (minDiff == 0) return -1;
        else return input + minDiff;
    }

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
        String test = "hhhhh";
        System.out.print(test.length());
    }

}
