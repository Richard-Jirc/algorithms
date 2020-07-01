import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

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


    /**RECT RANGE Search.
     * @param rect to search in.
     * @return a {@code Queue} object that contains all points
     */
    public Iterable<Point2D> range(RectHV rect) {
        return new Queue<>();
    }


    /**COMPARE helper function.
     * @return {@code true} if a < b in {@code x()} or {@code y()}.
     */
    private boolean lessX(Point2D a, Point2D b) {
        if (a != null && b != null) return (a.x() - b.x()) < 0;
        throw new IllegalArgumentException("lessX() argument contains null");
    }
    private boolean lessY(Point2D a, Point2D b) {
        if (a != null && b != null) return (a.y() - b.y()) < 0;
        throw new IllegalArgumentException("lessY() argument contains null");
    }

    /**DRAW. draw all points to {@code Std.draw}*/
    public void draw() {

    }
    public static void main(String[] args) {

    }
}
