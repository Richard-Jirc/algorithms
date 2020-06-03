
import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {

    private int count;
    private final Stack<LineSegment> storeResult;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        storeResult = new Stack<>();
        count = 0;
        Point[] searchSeq = new Point[points.length];
        System.arraycopy(points, 0, searchSeq, 0, points.length);

        double detection = Double.NEGATIVE_INFINITY;
        Point[] clusters = new Point[points.length];
        int increment = 0;
        for (Point origin : searchSeq) {
            if (origin == null) throw new IllegalArgumentException();
            Arrays.sort(points, origin.slopeOrder());
            for (int i = 0; i < points.length; i++) {
                if (i == 0) continue;
                if (i == 1) {
                    increment = 0;
                    detection = points[0].slopeTo(points[i]);
                    if (detection == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
                }
                if (i > 1) {
                    if (detection != points[0].slopeTo(points[i])) {
                        if (increment >= 2) { // found 3+ points in a row.
                            checkIfValid(origin, clusters, increment);
                        }
                        increment = 0;
                        detection = points[0].slopeTo(points[i]);
                    } else {
                        increment++;
                        if (i == points.length - 1) {
                            clusters[increment] = points[i];
                            checkIfValid(origin, clusters, increment);
                        }
                    }
                }
                clusters[increment] = points[i];
            }
        }
    }
    private void checkIfValid(Point origin, Point[] clusters, int increment) {
        Point[] group = new Point[increment + 1];
        for (int i = 0; i < group.length; i++) {
            group[i] = clusters[i];
        }
        Arrays.sort(group);

//        System.out.print("origin" + origin.toString() + "; ");
//        for (Point item : group) {
//            System.out.print(item.toString() + ", ");
//        }
//        System.out.print(" increment: " + increment);
//        System.out.println();

        if (origin.compareTo(group[0]) >= 0) {
//            System.out.println("** found but not starting Pt **");
            return;
        }
        storeResult.push(new LineSegment(origin, group[group.length - 1]));
        count++;
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        LineSegment[] finalResult = new LineSegment[storeResult.size()];
        for (LineSegment seg : finalResult) {
            seg = storeResult.pop();
        }
        return finalResult;
    }

    public static void main(String[] args) {
        Point o1 = new Point(0, 0);
        Point o2 = new Point(2, 0);
        Point o3 = new Point(4, 0);
        Point o4 = new Point(6, 0);
        Point o5 = new Point(-1, 0);
        Point o6 = new Point(2, 2);
        Point o7 = new Point(-2, -2);
        Point o8 = new Point(-1, -1);
        Point[] test = {o1, o2, o3, o4, o5, o6, o7, o8};
        FastCollinearPoints Do = new FastCollinearPoints(test);

        System.out.println(Do.numberOfSegments());
    }
}
