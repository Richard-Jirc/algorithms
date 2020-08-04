/* AlgorithmII-CHAPTER 1 ASSIGNMENT: SAP */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class SAP {

    private final Digraph graph; // the digraph that need to be processed
    private int[] edgeTo, distTo;

    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("constructor argument null");
        graph = G;
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
    }

    /**
     * find the shortest ancestral path and return its length.
     * @param v first
     * @param w second
     * @return {@code length} of shortest ancestral path, {@code -1} when the path do not exist.
     */
    public int length(int v, int w) {
        checkV(v, w);
        initializeArray(edgeTo); // initialize helper arrays.
        initializeArray(distTo);
        


        return 0;
    }
    /** Override version of above.
     * @return find the shortest in any combination between {@code v & w}.
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    /**
     * find the common ancestor sitting in the shortest ancestral path.
     * @return {@code index} of common ancestor, {@code -1} if do not exist.
     */
    public int ancestor(int v, int w) {
        checkV(v, w);

        return 1;
    }
    /** Override version of above.
     * @return find the shortest anyway.
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    /** helper function to check v&w arguments */
    private void checkV(int v, int w) {
        if (v < 0 || v >= graph.V() || w < 0 || w >= graph.V()) throw new IllegalArgumentException("vertex out of range");
    }

    /** wash every element to {@code -1} */
    private void initializeArray(int[] array) {
        Arrays.fill(array, -1);
    }



    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph test = new Digraph(in);
        System.out.print(test.toString());
    }
}
