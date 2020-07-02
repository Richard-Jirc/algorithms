import edu.princeton.cs.algs4.Point2D;

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
        public int compare(Point2D p, boolean useX) {
            double diff;
            if (useX) diff = this.pt.x() - p.x();
            else diff = this.pt.y() - p.y();
            return (int) diff;
        }
    }
    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }
    public boolean isEmpty() { return root == null; }
    public int size() {
        return size;
    }

    /**Kd-Tree INSERTION function.
     * @param p to insert
     */
    public void insert(Point2D p) {
        root = put(root, p, true);
    }
    private Node put(Node x, Point2D p, boolean useX) {
        if (x == null) return new Node(p);
        int cmp = x.compare(p, useX);
        if (cmp > 0) x.left = put(x.left, p, !useX);
        else if (cmp < 0) x.right = put(x.right, p, !useX);
        else x.pt = p;
    }

    public static void main(String[] args) {

    }
}
