/* AlgorithmII-CHAPTER 1 ASSIGNMENT: SAP */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class SAP {

    private final Digraph graph; // defensive copy of the digraph
    private final int[] edgeToV, distToV, edgeToW, distToW; // BFS search arrays

    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("constructor argument null");
        graph = G;
        edgeToV = new int[G.V()];
        distToV = new int[G.V()];
        edgeToW = new int[G.V()];
        distToW = new int[G.V()];
    }

    /**
     * find the shortest ancestral path and return its length.
     * @param v first
     * @param w second
     * @return {@code length} of shortest ancestral path, {@code -1} when the path do not exist.
     * @throws IllegalArgumentException when index out of range
     */
    public int length(int v, int w) {
        checkV(v, w);
        // if (v == w) return 0;
        Arrays.fill(edgeToV, -1); // initialize helper arrays.
        Arrays.fill(distToV, 0);
        Arrays.fill(edgeToW, -1);
        Arrays.fill(distToW, 0);
        Queue<Integer> fromV = new Queue<>();
        Queue<Integer> fromW = new Queue<>();

        fromV.enqueue(v);
        fromW.enqueue(w);
        int depth = 1;
        while (!fromV.isEmpty() && !fromW.isEmpty()) {
            int x = fromV.dequeue();
            int y = fromW.dequeue();
            Iterable<Integer> adjX = graph.adj(x);
            Iterable<Integer> adjY = graph.adj(y);
            for (int i : adjX) {
                if (edgeToV[i] != -1) continue;
                edgeToV[i] = x;
                distToV[i] = depth;
                for (int k : adjY) {
                    if (i == k) {
                        // found matching common ancestor!
                        return 0;
                    }
                    if (edgeToW[k] != -1) continue;
                    edgeToW[k] = y;
                    distToW[k] = depth;
                    fromW.enqueue(k);
                }
                fromV.enqueue(i);
            }

        }
        // when the algorithm reaches here it means no common ancestor is found.
        return -1;
    }
    /** {@code SET} version of above */
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

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph test = new Digraph(in);
        System.out.print(test.toString());
    }
}
