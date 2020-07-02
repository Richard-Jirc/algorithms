import edu.princeton.cs.algs4.*;

public class PointSET {
    private final Stack<Point2D> stack;

    public PointSET() {
        stack = new Stack<>();
    }
    public boolean isEmpty() { return stack.isEmpty(); }
    public int size() { return stack.size(); }

    public void insert(Point2D p) { if (!contains(p)) stack.push(p); }

    public boolean contains(Point2D p) {
        for (Point2D i : stack) {
            if (p.equals(i)) return true;
        }
        return false;
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D i : stack) {
            StdDraw.filledCircle(i.x(), i.y(), 0.005);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> result = new Stack<>();
        for (Point2D i : stack) {
            if (rect.contains(i)) result.push(i);
        }
        return result;
    }

//    public Point2D nearest(Point2D p) {
//
//    }

    public static void main(String[] args) {

    }
}
