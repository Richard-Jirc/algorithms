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
        /**@return {@code true} if X should be compared in this depth*/
        public boolean compareX() { return (depth % 2) == 1; }
        public void setDepth(int d) { depth = d; }
        public int comparePt(Point2D p) {
            double diff;
            if (compareX()) diff = this.pt.x() - p.x();
            else diff = this.pt.y() - p.y();
            return (int) diff;
        }
    }

    private Node root;

    public KdTree() {
        root = null;
    }
    public boolean isEmpty() { return root == null; }

    /**CONTAINS
     * @param p Point2D to search
     * @return {@code true} if p exists in the tree, {@code null} if p is null
     */
    public boolean contains(Point2D p) {
        if (p == null) return false;
        Node x = root;
        while (x != null) {
            int cmp = x.comparePt(p); // compare method wrapped in Node datatype
            if (cmp > 0) x = x.left;
            else if (cmp < 0) x = x.right;
            else return true;
        }
        return false;
    }

    /**Kd-Tree INSERTION*/
    public void insert(Point2D p) {
        root = new Node(p, 1);
    }

    public static void main(String[] args) {

    }
}
