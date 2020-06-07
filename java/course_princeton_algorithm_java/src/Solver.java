import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;

public class Solver {
    MinPQ<SearchNode> queue;
    private final boolean solvable;
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        int moves, manhattan, priority;
        public SearchNode(Board step) {
            board = step;
            manhattan = step.manhattan();
        }
        @Override
        public int compareTo(SearchNode node) {
            return this.priority - node.priority;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        solvable = this.isSolvable();
        queue = new MinPQ<>();
    }
    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        if (!solvable) return -1;


        return 0;
    }
    public Iterable<Board> solution() {
        if (!solvable) return null;

        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return null;
            }
        };
    }

    public static void main(String[] args) {

    }
}
