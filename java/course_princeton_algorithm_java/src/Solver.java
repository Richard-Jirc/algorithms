import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Solver {
    private MinPQ<SearchNode> queue;
    private Stack<Board> solutionSeq;
    private final boolean solvable;
    private int totalMoves = 0;
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        Board previous;
        int moves, manhattan, priority;
        public SearchNode(Board content, int move, Board prev) {
            board = content;
            previous = prev;
            moves = move;
            manhattan = content.manhattan();
            priority = manhattan + moves; // the critical priority variable.
        }
        @Override
        public int compareTo(SearchNode node) {
            return this.priority - node.priority;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        if (initial.isGoal()) {
            solvable = true;
            return;
        }
        solvable = this.isSolvable();
        queue = new MinPQ<>();
        solutionSeq = new Stack<>();
        SearchNode first = new SearchNode(initial, 0, null);
        queue.insert(first);
        this.search();
    }
    private void search() {
        SearchNode least;
        do {
            least = queue.delMin();
            solutionSeq.push(least.board);
            totalMoves++;
            Iterable<Board> children = least.board.neighbors();
            for (Board each : children) {
                if (least.previous != null) {
                    if (!each.equals(least.previous)) {
                        queue.insert(new SearchNode(each, totalMoves, least.board));
                    }
                } else {
                    queue.insert(new SearchNode(each, totalMoves, least.board));
                }
            }
        } while (!least.board.isGoal());
    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        if (!solvable) return -1;
        return totalMoves;
    }
    public Iterable<Board> solution() {
        if (!solvable) return null;
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return solutionSeq.iterator();
            }
        }; // lambda allowed here.
    }

    public static void main(String[] args) {
        int[][] array = new int[3][3];
        int k = 1;
        for (int[] i : array) {
            int p = 0;
            for (int j : i) {
                i[p++] = k;
                k++;
            }
        }
        array[2][1] = 0;
        array[2][2] = 8;
        Board test = new Board(array);
        System.out.println(test.toString());

        Solver solveIt = new Solver(test);
        System.out.println("totalMoves:" + solveIt.totalMoves);
        int huh = 1;
        for (Board step : solveIt.solution()) {
            System.out.println("Step " + huh + ": " + step.toString());
            huh++;
        }
    }
}
