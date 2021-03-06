import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Solver {
    private final Stack<Board> solutionSeq; // queue to store removed least priority board.
    private boolean solvable;

    /** HELPER CLASS SearchNode */
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        SearchNode previous;
        int moves, manhattan, priority;
        public SearchNode(Board content, int move, SearchNode prev) {
            board = content;
            previous = prev;
            moves = move;
            if (content.isGoal()) priority = 0;
            else {
                manhattan = content.manhattan();
                priority = manhattan + moves; // the critical priority function.
            }
        }
        public int getPriority() { return priority; }
        public String toString() {
            return board.toString();
        }
        @Override
        public int compareTo(SearchNode node) {
            if (this.getPriority() == node.getPriority()) {
                /* if tie by priority function,
                   the one with bigger moves are placed closer to the top */
                return this.moves - node.moves; //
            }
            return this.getPriority() - node.getPriority();
        }
    }

    /** CONSTRUCTOR
     *  create two minPQs for initial Board and its twin()
     *  then lockstep search for each of them.
     */
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        solutionSeq = new Stack<>();

        MinPQ<SearchNode> queue = new MinPQ<>();
        MinPQ<SearchNode> queueMirror = new MinPQ<>();

        queue.insert(new SearchNode(initial, 0, null));
        queueMirror.insert(new SearchNode(initial.twin(), 0, null));
        SearchNode result, resultMirror;
//        int count = 0;
        while (true) {
//            count++;
            result = pushSearch(queue);
            resultMirror = pushSearch(queueMirror);
            if (result.board.isGoal()) {
                solvable = true;
                traceSolution(result);
                break;
            } else if (resultMirror.board.isGoal()) {
                solvable = false;
                break;
            }
        }
//        System.out.println(count);
    }

    /** LOCK STEP SEARCH HELPER FUNCTION
     * return the minimum SearchNode in minPQ,
     * insert its *NON-duplicate* children if not goal
     * @param minQueue the minPQ to operate on
     */
    private SearchNode pushSearch(MinPQ<SearchNode> minQueue) {
        SearchNode least = minQueue.delMin();
        if (least.board.isGoal()) return least;

        Iterable<Board> children = least.board.neighbors();

//        Stack<SearchNode> history = new Stack<>();
//        SearchNode leastCp = least;
//        boolean duplicate;

        for (Board each : children) {
            SearchNode nextNode = new SearchNode(each, least.moves + 1, least);
//            duplicate = false;
//            while (leastCp != null) {
//                history.push(leastCp);
//                leastCp = leastCp.previous;
//            }
//            for (SearchNode i : history) {
//                if (i.board.equals(each) && i.moves < least.moves + 1) {
//                    duplicate = true;
//                    break;
//                }
//            }
//            if (!duplicate) minQueue.insert(nextNode);
            if (least.previous != null) {
                if (!each.equals(least.previous.board)) {
                    minQueue.insert(nextNode);
                }
            } else {
                minQueue.insert(nextNode);
            }
        }
        return least;
    }

    /** SOLUTION STACK CONSTRUCTOR
     *  once got the final solution,
     *  trace backward through the game tree to construct the solution path.
    */
    private void traceSolution(SearchNode finalResult) {
        while (finalResult != null) {
            solutionSeq.push(finalResult.board);
            finalResult = finalResult.previous;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!solvable) return -1;
        return Math.max(solutionSeq.size() - 1, 0);
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
        int[][] array = new int[][]{{0, 6, 2}, {4, 5, 1}, {7, 8, 3}};
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
