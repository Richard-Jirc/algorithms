/*
 * W2 Assignment: Deque Class
 *
 *
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> list = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            list.enQueue(item);
        }

        int pick = StdIn.readInt();
        for (int i = 0; i < pick; i++) {
            StdOut.println(list.deQueue());
        }
    }

}
