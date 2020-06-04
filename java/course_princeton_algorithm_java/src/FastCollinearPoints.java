import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {

    private int count;
    private final Stack<LineSegment> storeResult;
    private final LineSegment[] finalResult;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        storeResult = new Stack<>();
        count = 0;
        double detection = Double.NEGATIVE_INFINITY;
        Point[] clusters = new Point[points.length];
        int increment = 0;

        Point[] searchSeq = new Point[points.length]; // to maintain an array for traversing points in a proper order
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException(); // check null by the way.
            searchSeq[i] = points[i];
        }

        for (Point origin : searchSeq) {
            Arrays.sort(points, origin.slopeOrder());
            for (int i = 0; i < points.length; i++) {
                if (i == 0) continue;
                if (i == 1) {
                    increment = 0;
                    detection = points[0].slopeTo(points[i]);
                    if (detection == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException(); // this finds duplicate points regarding to the origin, as long as there's more than one.
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
                        if (i == points.length - 1 && increment >= 2) { // you should also check if there are 3+ points in a row here!
                            clusters[increment] = points[i];
                            checkIfValid(origin, clusters, increment);
                        }
                    }
                }
                clusters[increment] = points[i];
            }
        }
        finalResult = new LineSegment[storeResult.size()];
        for (int i = 0; i < count; i++) {
            finalResult[i] = storeResult.pop();
        }

    }
    private void checkIfValid(Point origin, Point[] clusters, int increment) {
        Point[] group = new Point[increment + 1];
        System.arraycopy(clusters, 0, group, 0, group.length);
        Arrays.sort(group);
        if (origin.compareTo(group[0]) >= 0) { return; }
        storeResult.push(new LineSegment(origin, group[group.length - 1]));
        count++;
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[finalResult.length]; // defensive copied array to return.
        System.arraycopy(finalResult, 0, copy, 0, finalResult.length);
        return copy;
    }

    public static void main(String[] args) {
        Point o1 = new Point(-1, 1);
        Point o2 = new Point(0, 1);
        Point o3 = new Point(1, 1);
        Point o4 = new Point(-1, 0);
        Point o5 = new Point(0, 0);
        Point o6 = new Point(1, 0);
        Point o7 = new Point(-1, -1);
        Point o8 = new Point(0, -1);
        Point o9 = new Point(1, -1);
        Point[] test = {o1, o2, o3, o4, o5, o6, o7, o8, o9};
        FastCollinearPoints hey = new FastCollinearPoints(test);
        System.out.println(hey.numberOfSegments());
//        for (LineSegment line : hey.segments()) {
//            System.out.println(line.toString());
//        }
    }
}
