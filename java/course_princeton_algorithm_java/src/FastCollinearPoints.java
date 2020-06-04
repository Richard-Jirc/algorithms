import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {
    private int count;
    private final Stack<LineSegment> storeResult;
    private final LineSegment[] finalResult;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("array null");

        storeResult = new Stack<>();
        count = 0;
        double detection = Double.NEGATIVE_INFINITY;
        int increment = 0;
        Point[] array = new Point[points.length];
        Point[] clusters = new Point[points.length];

        Point[] searchSeq = new Point[array.length]; // to maintain an array for traversing points in a proper order
        for (int i = 0; i < array.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("element null"); // check null by the way.
            searchSeq[i] = points[i];
            array[i] = points[i];
        }

        for (Point origin : searchSeq) {
            Arrays.sort(array, origin.slopeOrder());
            for (int i = 0; i < array.length; i++) {
                if (i == 0) continue;
                if (i == 1) {
                    increment = 0;
                    detection = array[0].slopeTo(array[i]);
                    if (detection == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("duplicate point"); // this finds duplicate points regarding to the origin, as long as there's more than one.
                }
                if (i > 1) {
                    if (detection != array[0].slopeTo(array[i])) {
                        if (increment >= 2) { // found 3+ points in a row.
                            checkIfValid(origin, clusters, increment);
                        }
                        increment = 0;
                        detection = array[0].slopeTo(array[i]);
                    } else {
                        increment++;
                        if (i == array.length - 1 && increment >= 2) { // you should also check if there are 3+ points in a row here!
                            clusters[increment] = array[i];
                            checkIfValid(origin, clusters, increment);
                        }
                    }
                }
                clusters[increment] = array[i];
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
