import edu.princeton.cs.algs4.Point2D;

/**Kd-Tree for chapter 5 assignment
 * implementing red-black tree data structure to ensure o(logN) time for operations
 * */
public class KdTree {
    private static class Node {
        private final Point2D pt;
        private int depth;
        private Node left, right;
        private int size;
        public Node(Point2D p, int d) {
            pt = p;
            depth = d;
        }
        /**@return TRUE if X should be compared in this depth*/
        public boolean compareX() { return (depth % 2) == 1; }
        public void setDepth(int d) { depth = d; }

    }
    Node root;

    public KdTree() {
        root = null;
    }
    public boolean isEmpty() { return root == null; }

    /**INSERTION: public method*/
    public void insert(Point2D p) {

    }

    public static void main(String[] args) {

    }
}
