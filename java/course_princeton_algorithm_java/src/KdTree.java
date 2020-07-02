import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private static class Node {
        private Point2D pt;
        private RectHV rect;
        private Node left, right;
        private final boolean x;
        public Node(Point2D p, boolean useX) {
            pt = p;
            x = useX;
            if (useX) rect = new RectHV(0, 0, p.x(), 1);
            else rect = new RectHV()
        }

        /**COMPARE {@code Node} with a {@code Point2D}
         * @param p to compare with
         * @return {@code int > 0} if search should go LEFT.
         */
        public double compare(Point2D p) {
            if (x) return this.pt.x() - p.x();
            else return this.pt.y() - p.y();
        }

        public boolean worthSearch(Point2D target) {
            return true;
        }
        public String toString() {
            StringBuilder string = new StringBuilder();
            if (left != null) string.append(left.pt.toString());
            string.append("<=");
            if (x) string.append("*");
            string.append(pt.toString());
            if (!x) string.append("*");
            string.append("=>");
            if (right != null) string.append(right.pt.toString());
            string.append("\n");
            return string.toString();
        }
    }
    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }
    public boolean isEmpty() { return root == null; }
    public int size() { return size; }

    /**Kd-Tree INSERTION function.
     * @param p to insert
     */
    public void insert(Point2D p) {
        root = put(root, p, true);
    }
    private Node put(Node x, Point2D p, boolean useX) {
        if (x == null) {
            size++;
            return new Node(p, useX);
        }
        double cmp = x.compare(p);
        if (cmp > 0) x.left = put(x.left, p, !useX);
        else if (cmp < 0) x.right = put(x.right, p, !useX);
        else {
            if (!x.pt.equals(p)) x.right = put(x.right, p, !useX);
            else x.pt = p;
        }
        return x;
    }

    /**Kd-Tree CONTAINS function.
     * @param p point to search
     * @return {@code true} if matching point is found.
     */
    public boolean contains(Point2D p) {
        if (p == null) return false;
        if (root == null) return false;
        return search(root, p, true);
    }
    private boolean search(Node x, Point2D p, boolean useX) {
        if (x == null) return false;
        double cmp = x.compare(p);
        if (cmp > 0) return search(x.left, p, !useX);
        else if (cmp < 0) return search(x.right, p, !useX);
        else return x.pt.equals(p);
    }

    /**RECTANGLE SEARCH
     *
     * @param rect to search
     * @return a {@code Queue} object as {@code Iterable};
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> list = new Queue<>();

        return list;
    }
    private void search()

    /**NEAREST POINT SEARCH
     * @param p as target point
     * @return the nearest {@code Point2D}
     */
    public Point2D nearest(Point2D p) {
        Point2D closest = root.pt;
        return new Point2D(0,0);
    }
//    private Point2D near(Node x, Point2D p) {
//
//    }

    /**DETERMINE if new entry point is a CLOSER ONE.
     * @param target supplied target Point
     * @param p first Pt
     * @param q second Pt
     * @return {@code true} if q is CLOSER, equal returns {@code false}.
     */
    private boolean closer(Point2D target, Point2D p, Point2D q) {
        double pDis = Math.pow(target.x() - p.x(), 2) + Math.pow(target.y() - p.y(), 2);
        double qDis = Math.pow(target.x() - q.x(), 2) + Math.pow(target.y() - q.y(), 2);
        return qDis < pDis;
    }
    public static void main(String[] args) {
        KdTree test = new KdTree();
        test.insert(new Point2D(0.2, 0.3));
        test.insert(new Point2D(0.5, 0.9));
        test.insert(new Point2D(0.8, 0.2));
        test.insert(new Point2D(0.9, 1.5));
        test.insert(new Point2D(0.2, 0.5));
        test.insert(new Point2D(0.05, 0.5));
        System.out.print(test.root.toString());
        System.out.println(test.size());
        System.out.print(test.contains(new Point2D(0.5, 0.2)));
    }
}
