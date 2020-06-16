import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class Solver {

    private final MinPQ<SearchNode> queue, queueMirror; // minPQ to track game tree
    private final Queue<Board> solutionSeq; // queue to store removed least priority board.

    private boolean solvable;

    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        Board previous;
        int moves, manhattan, priority;
        public SearchNode(Board content, int move, Board prev) {
            board = content;
            previous = prev;
            moves = move;
            if (content.isGoal()) priority = 0;
            else {
                manhattan = content.manhattan();
                priority = manhattan + moves; // the critical priority function.
            }
        }
        public String toString() {
            return board.toString();
        }
        @Override
        public int compareTo(SearchNode node) {
            if (this.priority == node.priority) {
                /* if tie by priority function,
                   the one with bigger moves are placed closer to the top */
                return node.moves - this.moves; //
            }
            return this.priority - node.priority;
        }
        public static boolean less(SearchNode a, SearchNode b) {
            return a.compareTo(b) < 0;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        queue = new MinPQ<>();
        queueMirror = new MinPQ<>();
        solutionSeq = new Queue<>();

        if (initial.isGoal()) {
            solvable = true;
            return;
        }

        queue.insert(new SearchNode(initial, 0, null));
        queueMirror.insert(new SearchNode(initial.twin(), 0, null));

        this.lockstepSearch();
    }
    private void lockstepSearch() {
        SearchNode result, resultMirror;
        int move = 0;
        do {
            move++;
            result = pushSearch(queue, move);
            resultMirror = pushSearch(queueMirror, move);

            solutionSeq.enqueue(result.board);

            if (result.board.isGoal()) {
                solvable = true;
                break;
            } else if (resultMirror.board.isGoal()) {
                solvable = false;
                break;
            }
        } while (move < 10000);
    }
    private SearchNode pushSearch(MinPQ<SearchNode> minQueue, int currentDepth) {
        SearchNode least;
        do {
            least = minQueue.delMin();
            if (least.board.isGoal()) break;
        } while (least.moves != currentDepth - 1);

        Iterable<Board> children = least.board.neighbors();

        for (Board each : children) {
            SearchNode nextNode = new SearchNode(each, currentDepth, least.board);
            if (least.previous != null) {
                if (!each.equals(least.previous)) {
                    minQueue.insert(nextNode);
                }
            } else {
                minQueue.insert(nextNode);
            }
        }
        return least;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!solvable) return -1;
        return solutionSeq.size() - 1;
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
        int[][] array = new int[][]{{0, 1, 3}, {4, 5, 2}, {7, 6, 8}};
        Board test = new Board(array);
        System.out.println(test.toString());

        Solver solveIt = new Solver(test);
        System.out.println("totalMoves:" + solveIt.moves());
        int huh = 0;
        if (solveIt.isSolvable()) {
            for (Board step : solveIt.solution()) {
                System.out.println("Step " + huh + ": " + step.toString());
                huh++;
            }
        } else {
            System.out.println(solveIt.solution());
        }

    }
}
