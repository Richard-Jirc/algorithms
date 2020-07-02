import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static class Node {
        private Point2D pt;
        private RectHV rect;
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
        /**COMPARE {@code Node} with a {@code Point2D}
         * @param p to compare with
         * @return {@code int > 0} if search should go LEFT.
         */
        public double compare(Point2D p) {
            if (x) return this.pt.x() - p.x();
            else return this.pt.y() - p.y();
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
     * @param rect to search
     * @return a {@code Queue} object as {@code Iterable};
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> list = new Queue<>();

        return list;
    }
    private void search() {

    }

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

    public void draw() {
        if (root != null) draw(root);
    }
    private void draw(Node node) {
        StdDraw.setPenRadius(0.002);
        if (node.x) {
            StdDraw.setPenColor(StdDraw.RED); // draw vertical line!
            StdDraw.line(node.pt.x(), node.rect.ymin(), node.pt.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE); // draw horizontal line!
            StdDraw.line(node.rect.xmin(), node.pt.y(), node.rect.xmax(), node.pt.y());
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(node.pt.x(), node.pt.y(), 0.005);
        if (node.left != null) draw(node.left);
        if (node.right != null) draw(node.right);
    }

    /**DETERMINE if new entry point is a CLOSER ONE.
     * @param target supplied target Point
     * @param p first Pt
     * @param q second Pt
     * @return {@code true} if q is CLOSER, equal or bigger returns {@code false}.
     */
    private boolean closer(Point2D target, Point2D p, Point2D q) {
        return target.distanceSquaredTo(q) < target.distanceSquaredTo(p);
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
