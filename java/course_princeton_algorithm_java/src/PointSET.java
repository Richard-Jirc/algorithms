import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;


public class PointSET {
    private final RedBlackBST<Point2D, Point2D> tree;
    public PointSET() {
        tree = new RedBlackBST<>();
    }
    public boolean isEmpty() { return tree.isEmpty(); }
    public int size() { return tree.size(); }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("insert null");
        tree.put(p, p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        return tree.contains(p);
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D i : tree.keys()) {
            StdDraw.filledCircle(i.x(), i.y(), 0.003);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect range null");
        if (isEmpty()) return null;
        Stack<Point2D> result = new Stack<>();
        for (Point2D i : tree.keys()) {
            if (rect.contains(i)) result.push(i);
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("nearest null");
        if (isEmpty()) return null;
        Point2D nearest = new Point2D(0.5, 0.5);
        double distance = 100;
        double current;
        for (Point2D i : tree.keys()) {
            current = p.distanceSquaredTo(i);
            if (current < distance) {
                nearest = i;
                distance = current;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        PointSET test = new PointSET();
        test.insert(new Point2D(0, 0));
    }
}
