/** CHAPTER 5 ASSIGNMENT: Kd-Tree*/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static class Node {
        private final Point2D pt;
        private final RectHV rect;
        private Node left, right;
        private final boolean x;
        public Node(Point2D p, boolean useX, RectHV area) {
            pt = p;
            x = useX;
            rect = area;
        }
        public RectHV leftRect() {
            if (x) return new RectHV(rect.xmin(), rect.ymin(), pt.x(), rect.ymax());
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), pt.y());
        }
        public RectHV rightRect() {
            if (x) return new RectHV(pt.x(), rect.ymin(), rect.xmax(), rect.ymax());
            return new RectHV(rect.xmin(), pt.y(), rect.xmax(), rect.ymax());
        }

        public boolean nearGoLeft(Point2D target) {
            if (x) return target.x() < pt.x();
            else return target.y() < pt.y();
        }

        /** COMPARE {@code Node} with a {@code Point2D}
         * @param p to compare with
         * @return {@code int > 0} if search should go LEFT.
         */
        public double compare(Point2D p) {
            if (x) return this.pt.x() - p.x();
            else return this.pt.y() - p.y();
        }
    }
    private Node root;
    private int size;

    /** Kd-Tree CONSTRUCTOR*/
    public KdTree() {
        root = null;
        size = 0;
    }
    public boolean isEmpty() { return root == null; }
    public int size() { return size; }

    /** Kd-Tree INSERTION function.
     * if the insertion point's x/y ties with current node, go RIGHT!
     * @param p to insert
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("insert null");
        RectHV canvas = new RectHV(0, 0, 1, 1);
        root = put(root, p, true, canvas);
    }
    private Node put(Node x, Point2D p, boolean useX, RectHV rect) {
        if (x == null) {
            size++;
            return new Node(p, useX, rect);
        }
        double cmp = x.compare(p);
        if (cmp > 0) x.left = put(x.left, p, !useX, x.leftRect());
        else if (cmp < 0 || !x.pt.equals(p)) x.right = put(x.right, p, !useX, x.rightRect());
        return x;
    }

    /** Kd-Tree CONTAINS function.
     * @param p point to search
     * @return {@code true} if matching point is found.
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        if (root == null) return false;
        return search(root, p);
    }
    private boolean search(Node x, Point2D p) {
        if (x == null) return false;
        double cmp = x.compare(p);
        if (cmp > 0) return search(x.left, p);
        else if (cmp < 0 || !x.pt.equals(p)) return search(x.right, p);
        else return true;
    }

    /** RECTANGLE SEARCH
     * @param q {@code RectHV} to search
     * @return a {@code Queue} object as {@code Iterable};
     */
    public Iterable<Point2D> range(RectHV q) {
        if (q == null) throw new IllegalArgumentException("RectHV range null");
        Queue<Point2D> list = new Queue<>();
        search(root, q, list);
        return list;
    }
    private void search(Node x, RectHV q, Queue<Point2D> queue) {
        if (x != null && x.rect.intersects(q)) {
            if (q.contains(x.pt)) queue.enqueue(x.pt);
            search(x.left, q, queue);
            search(x.right, q, queue);
        }
    }

    private Point2D closest;
    private double minD;

    /** NEAREST POINT SEARCH
     * @param p as target point
     * @return the nearest {@code Point2D}
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("nearest null");
        if (isEmpty()) return null;
        closest = root.pt;
        minD = 10;
        near(root, p);
        return closest;
    }
    private void near(Node x, Point2D target) {
        if (x == null) return;
        if (x.rect.distanceSquaredTo(target) > minD) return;
        double curD = x.pt.distanceSquaredTo(target);
        if (curD < minD) update(x.pt, curD);

        boolean whichWay = x.nearGoLeft(target);
        if (whichWay) near(x.left, target);
        else near(x.right, target);
        if (whichWay) near(x.right, target);
        else near(x.left, target);
    }
    private void update(Point2D p, double d) {
        closest = p;
        minD = d;
    }

    /** DRAW to StdDraw:
     * for debugging.*/
    public void draw() {
        if (root != null) draw(root);
    }
    private void draw(Node node) {
        double POINT_SIZE = 0.005;
        StdDraw.setPenRadius(0.001);
        if (node.x) {
            StdDraw.setPenColor(StdDraw.RED); // draw vertical line!
            StdDraw.line(node.pt.x(), node.rect.ymin(), node.pt.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE); // draw horizontal line!
            StdDraw.line(node.rect.xmin(), node.pt.y(), node.rect.xmax(), node.pt.y());
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(node.pt.x(), node.pt.y(), POINT_SIZE);
        if (node.left != null) draw(node.left);
        if (node.right != null) draw(node.right);
    }

    public static void main(String[] args) {
        KdTree test = new KdTree();
        test.insert(new Point2D(0, 0));
    }
}
