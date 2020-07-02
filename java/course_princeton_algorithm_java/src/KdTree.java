import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private static class Node {
        private Point2D pt;
        private Node left, right;
        public Node(Point2D p) {
            pt = p;
        }

        /**COMPARE {@code Node} with a {@code Point2D}
         * @param p to compare with
         * @param useX {@code true} to compare by {@code x()}, otherwise by {@code y()}.
         * @return {@code int > 0} if search should go LEFT.
         */
        public double compare(Point2D p, boolean useX) {
            if (useX) return this.pt.x() - p.x();
            else return this.pt.y() - p.y();
        }
        public String toString() {
            StringBuilder string = new StringBuilder();
            if (left != null) string.append(left.pt.toString());
            string.append("<=");
            string.append(pt.toString());
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
            return new Node(p);
        }
        double cmp = x.compare(p, useX);
        if (cmp > 0) x.left = put(x.left, p, !useX);
        else if (cmp < 0) x.right = put(x.right, p, !useX);
        else x.pt = p;
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
        double cmp = x.compare(p, useX);
        if (cmp > 0) return search(x.left, p, !useX);
        else if (cmp < 0) return search(x.right, p, !useX);
        else {
            if (x.pt.equals(p)) return true;
            return false;
        }
    }

    /**
     *
     * @param rect to search
     * @return a {@code Queue} object as {@code Iterable};
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> list = new Queue<>();
        
        return list;
    }

    public static void main(String[] args) {
        KdTree test = new KdTree();
        test.insert(new Point2D(0.2, 0.3));
        test.insert(new Point2D(0.5, 0.9));
        test.insert(new Point2D(0.8, 0.2));
        System.out.print(test.root.right.toString());
        System.out.println(test.size());
        System.out.print(test.contains(new Point2D(0.4, 0.2)));
    }
}
