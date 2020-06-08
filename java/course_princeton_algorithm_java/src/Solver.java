import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Solver {
    private MinPQ<SearchNode> queue, queueMirror;
    private Queue<Board> solutionSeq;
    private boolean solvable;
    private int totalMoves = 0, totalMirror = 0;
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        Board previous;
        int moves, manhattan, priority, hamming;
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
        queue = new MinPQ<>();
        queueMirror = new MinPQ<>();

        solutionSeq = new Queue<>();

        SearchNode first = new SearchNode(initial, 0, null);
        SearchNode firstMirror = new SearchNode(initial.twin(), 0, null);



        queue.insert(first);
        queueMirror.insert(firstMirror);
        this.searchLockStep();
    }

    private SearchNode lockstepSearch(MinPQ queue, int move) {
        
    }

    private void searchLockStep() {
        SearchNode least, leastMirror;
        while (totalMoves < 10000 && totalMirror < 10000) {
            least = queue.delMin();
            leastMirror = queueMirror.delMin();
            solutionSeq.enqueue(least.board);
            if (least.board.isGoal()) {
                solvable = true;
                break;
            } else if (leastMirror.board.isGoal()) {
                solvable = false;
                break;
            }
            totalMoves++;
            totalMirror++;
            Iterator<Board> children = least.board.neighbors().iterator();
            Iterator<Board> childrenMirror = leastMirror.board.neighbors().iterator();
            while (children.hasNext() && childrenMirror.hasNext()) {
                Board each = children.next();
                Board eachMirror = childrenMirror.next();
                SearchNode nextNode = new SearchNode(each, totalMoves, least.board);
                SearchNode nextNodeMirror = new SearchNode(eachMirror, totalMoves, least.board);
                if (least.previous != null) {
                    if (!each.equals(least.previous)) {
                        queue.insert(nextNode);
                    }
                } else {
                    queue.insert(nextNode);
                }
                if (leastMirror.previous != null) {
                    if (!eachMirror.equals(leastMirror.previous)) {
                        queueMirror.insert(nextNodeMirror);
                    }
                } else {
                    queueMirror.insert(nextNodeMirror);
                }
            }
        }
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
        array = new int[][]{{0, 1, 3}, {2, 5, 4}, {7, 8, 6}};
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
