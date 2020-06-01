import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String input = StdIn.readString();
        RandomizedQueue<Character> list = new RandomizedQueue<>();
        for (char i : input.toCharArray()) {
            list.enqueue(i);
        }
        for (int j = 0; j < k; j++) {
            char item = list.sample();
            for (int g = 0; g < k; g++) {
                StdOut.print(item);
            }
            StdOut.println();
        }
    }
}
