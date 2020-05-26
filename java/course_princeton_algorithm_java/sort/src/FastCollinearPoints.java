import java.util.Arrays;
import java.util.Comparator;

// Class 'Point' implements Comparable!

public class FastCollinearPoints {

    private LineSegment[] result;

    public FastCollinearPoints(Point[] points) {
        // check for exceptions
        if (points == null) throw new IllegalArgumentException("input null");
        for (Point a : points) {
            if (a == null) throw new IllegalArgumentException("point null");
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int k = i + 1; k < points.length; k++) {
                if (equal(points[i], points[k])) throw new IllegalArgumentException("repeated points");
            }
        }

        result = new LineSegment[points.length / 4 + 1];
        Point[] collinearPts = new Point[1];
        int segNum = 0;

        // COMPARE!!!!!
        int count = 0;
        for (Point ORIGIN : points) {
            Comparator<Point> c = ORIGIN.slopeOrder();
            Arrays.sort(points, c);

            if (count == 1) print(points);
            // Now the sort is not right!

            int counter = 1;
            for (int k = 1; k < points.length; k++) {
                double a = ORIGIN.slopeTo(points[k - 1]);
                double b = ORIGIN.slopeTo(points[k]);
                if (a == b) {
                    // Keep watching
                    if (counter == 1) System.out.println("Oh?");
                    counter++;
                } else {
                    if (counter >= 4) {
                        // Caught a series of collinear points more than 3.
                        System.out.println("I found a series! " + count);

                        collinearPts = new Point[counter];
                        System.arraycopy(points, k - counter, collinearPts, 0, counter);
                        Arrays.sort(collinearPts);

                        Point head = collinearPts[0];
                        Point tail = collinearPts[counter - 1];
                        if (ORIGIN == head) {
                            result[segNum] = new LineSegment(head, tail);
                            segNum++;
                            System.out.println("First guy Spotted!");
                        } else {
                            System.out.println("It seems that this is not a head");
                        }
                    }
                    counter = 1;
                }
            }
            count++;
        }
        LineSegment[] copy = new LineSegment[segNum];
        System.arraycopy(result, 0, copy, 0, segNum);
        result = copy;

    }

    private boolean less(Point a, Point b) {
        return a.compareTo(b) < 0;
    }

    private boolean equal(Point a, Point b) {
        return a.compareTo(b) == 0;
    }

    public int numberOfSegments() {
        return result.length;
    }

    public LineSegment[] segments() {
        return result;
    }

    // Comment Out After TEST
    private static void print(Point[] points) {
        for (Point i : points) {
            System.out.print(i.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Point[] test = new Point[7];
        for (int i = 1; i <= 5; i++) {
            test[i - 1] = new Point(i, i);
        }
        test[5] = new Point(8, 1);
        test[6] = new Point(10, 3);
        FastCollinearPoints hey = new FastCollinearPoints(test);
        System.out.println(hey.numberOfSegments());

    }

}
