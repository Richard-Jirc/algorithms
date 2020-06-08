import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Solver {
    private MinPQ<SearchNode> queue, queueMirror;
    private Queue<Board> solutionSeq;
    private boolean solvable;
    private int totalMoves = 0;
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        Board previous;
        int moves, manhattan, priority, hamming;
        public SearchNode(Board content, int move, Board prev) {
            board = content;
            previous = prev;
            moves = move;
            manhattan = content.manhattan();
            priority = manhattan + moves; // the critical priority function.
        }
        @Override
        public int compareTo(SearchNode node) {
            if (this.board.isGoal()) return -1;
            if (node.board.isGoal()) return 1;
            if (this.priority == node.priority) {
//                if (this.previous != null) {
//                    if (this.previous.equals(node.board)) return 1;
//                }
//                if (node.previous != null) {
//                    if (node.previous.equals(this.board)) return -1;
//                }
                return this.moves - node.moves;
            }
            return this.priority - node.priority;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        if (initial.isGoal()) {
            solvable = true;
            return;
        }
        queue = new MinPQ<>();
        queueMirror = new MinPQ<>();

        solutionSeq = new Queue<>();

        SearchNode first = new SearchNode(initial, 0, null);
        SearchNode firstMirror = new SearchNode(initial.twin(), 0, null);

        queue.insert(first);
        queueMirror.insert(firstMirror);
        this.lockstepSearch();
    }
    private void lockstepSearch() {
        SearchNode result, resultMirror;
        do {
            result = pushSearch(queue, totalMoves);
            resultMirror = pushSearch(queueMirror, totalMoves);
            totalMoves++;
            solutionSeq.enqueue(result.board);
            if (result.board.isGoal()) {
                solvable = true;
                totalMoves--;
                break;
            } else if (resultMirror.board.isGoal()) {
                solvable = false;
                break;
            }
        } while (totalMoves < 10000);
        if (result.board.isGoal()) solvable = true;
        else solvable = !resultMirror.board.isGoal();
    }
    private SearchNode pushSearch(MinPQ<SearchNode> minQueue, int move) {
        SearchNode least = minQueue.delMin();
        move++;
        Iterable<Board> children = least.board.neighbors();
        for (Board each : children) {
            SearchNode nextNode = new SearchNode(each, move, least.board);
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
        array = new int[][]{{5, 3, 6}, {8, 1, 2}, {7, 4, 0}};
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
