package self.symbolTable;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeRB {
    private final boolean RED = true;
    private final boolean BLACK = false;

    private static class Node {
        private Point2D pt;
        private int depth;
        private Node left, right;
        private int size;
        private boolean color;
        public Node(Point2D p, int d, boolean col) {
            pt = p;
            depth = d;
            color = col;
        }

        /**X or Y coordinate to COMPARE.
         * ODD depth => {@code x}, EVEN depth => {@code y}
         * @return {@code true} if X should be compared in this depth*/
        public boolean compareX() { return (depth % 2) == 1; }

        public void setDepth(int d) { depth = d; }
        public void setPt(Point2D p) { pt = p; }

        /**COMPARING a node with a {@code Point2D} based on depth
         * @param p to compare with
         * @return {@code >0} if {@code this.pt > p}
         */
        public int comparePt(Point2D p) {
            double diff;
            if (compareX()) diff = this.pt.x() - p.x();
            else diff = this.pt.y() - p.y();
            return (int) diff;
        }
        public int compare(Point2D p, boolean useX) {
            double diff;
            if (useX) diff = this.pt.x() - p.x();
            else diff = this.pt.y() - p.y();
            return (int) diff;
        }
    }
    private Node root;

    public KdTreeRB() {
        root = null;
    }
    public boolean isEmpty() { return root == null; }


    /**Kd-Tree CONTAINS
     * @param p {@code Point2D} to search
     * @return {@code true} if p exists in the tree, {@code null} if p is null
     */
    public boolean contains(Point2D p) {
        if (p == null) return false;
        Node x = root;
        boolean useX = true;
        while (x != null) {
            if (!isRED(x)) useX = !useX;
            int cmp = x.compare(p, useX); // compare method wrapped in Node datatype
            if (cmp > 0) x = x.left;
            else if (cmp < 0) x = x.right;
            else return true;
        }
        return false;
    }

    /**Kd-Tree INSERTION
     * @param p {@code Point2D} to insert
     * */
    public void insert(Point2D p) {
        root = put(root, p, 1);
    }
    private Node put(Node x, Point2D p, int depth) {
        if (x == null) return new Node(p, depth + 1, RED);
        int cmp = x.comparePt(p);
        if (cmp > 0) x.left = put(x.left, p, x.depth);
        else if (cmp < 0) x.right = put(x.right, p, x.depth);
        else x.setPt(p);

        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColor(x);
        return x;
    }


    /**RECT RANGE Search.
     * @param rect to search in.
     * @return a {@code Queue} object that contains all points
     */
    public Iterable<Point2D> range(RectHV rect) {
        return new Queue<>();
    }

    /**DRAW. draw all points to {@code Std.draw}*/
    public void draw() {

    }

    /**IS-RED helper function.
     * the reason to define this function outside {@code Node} definition is to handle {@code null} cases.
     * @param x to examine
     * @return {@code true} if RED, {@code false} if {@code input} is {@code null}
     */
    private boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
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

    /**BST BASIC OPERATIONS */

    /**Rotate
     * @param head the reference node to rotate.
     * */
    private Node rotateLeft(Node head) {
//        assert head.right.isRED();
        Node r = head.right;
        head.right = r.left;
        r.left = head;
        r.color = head.color;
        head.color = RED;
        return r;
    }
    private Node rotateRight(Node head) {
//        assert head.left.isRED();
        Node l = head.left;
        head.left = l.right;
        l.right = head;
        l.color = head.color;
        head.color = RED;
        return l;
    }
    private void flipColor(Node head) {
        head.color = RED;
        head.left.color = BLACK;
        head.right.color = BLACK;
    }


    public static void main(String[] args) {

    }
}
