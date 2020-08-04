/* AlgorithmII-CHAPTER 1 ASSIGNMENT
 SAP's helper class DeluxeBFS */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class DeluxeBFS {

    private final int[] edgeTo, distTo;
    private final boolean[] marked;

    public DeluxeBFS(Digraph G, int s) {
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    public boolean access(int v) {
        return marked[v];
    }
    public int shortestDis(int v) {
        int j = v;
        while (j != edgeTo[j]) {
            j = edgeTo[j];
        }
        return distTo[v];
    }

    // standard breadth first search
    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        int depth = 0;
        while (!q.isEmpty()) {
            int pop = q.dequeue();
            Iterable<Integer> adj = G.adj(pop);
            depth++;
            for (int i : adj) {
                if (marked[i]) continue;
                edgeTo[i] = pop;
                distTo[i] = depth;
                q.enqueue(i);
            }
        }
    }

}
