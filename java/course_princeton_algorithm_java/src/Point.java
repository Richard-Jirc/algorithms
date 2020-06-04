/******************************************************************************
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Draws this point to standard draw. */
    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        double difX = that.x - x;
        double difY = that.y - y;
        if (difX == 0 && difY == 0) return Double.NEGATIVE_INFINITY; // identical points, min possible value
        else if (difY == 0) return +0.0; // horizontal
        else if (difX == 0) return Double.POSITIVE_INFINITY; // vertical, max possible value
        else return difY / difX;
    }

    public int compareTo(Point that) {
        int difY = y - that.y;
        int difX = x - that.x;
        if (difY < 0) return -1;
        else if (difY > 0) return 1;
        else return Integer.compare(difX, 0);
    }

    public Comparator<Point> slopeOrder() {
        Point host = this;
        return new SlopeComparator(host);
    }
    private static class SlopeComparator implements Comparator<Point> {
        Point origin;
        public SlopeComparator(Point host) {
            origin = host;
        }
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = origin.slopeTo(o1);
            double slope2 = origin.slopeTo(o2);
            return Double.compare(slope1, slope2);
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Point origin = new Point(0, 0);
        Point x = new Point(2, 0);
        Point y = new Point(-4, 4);
        Point[] test = {origin, x, y, null, null, null};
        Arrays.sort(test);
        for (Point i : test) {
            System.out.println(i);
        }
    }
}
