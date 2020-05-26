/*

W3 Assignment!

 */


public class BruteCollinearPoints {

    private int segNum;
    private LineSegment[] temp = new LineSegment[1];

    public BruteCollinearPoints(Point[] points) {

        // check for exceptions
        if (points == null) throw new IllegalArgumentException("input null");
        for (Point a : points) {
            if (a == null) throw new IllegalArgumentException("point null");
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int k = i + 1; k < points.length; k++) {
                if (points[i] == points[k]) throw new IllegalArgumentException("repeated points");
            }
        }

        segNum = 0;
        ptSort(points);

        for (int i = 0; i < (points.length - 3); i++) {
            for (int j = i + 1; j < (points.length - 2); j++) {
                for (int k = j + 1; k < (points.length - 1); k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        double a = points[i].slopeTo(points[j]);
                        double b = points[i].slopeTo(points[k]);
                        double c = points[i].slopeTo(points[m]);
                        if (a == b) {
                            if (a == c) {
                                segNum++;
                                LineSegment bingo = new LineSegment(points[i], points[m]);
                                temp = push(temp, bingo);
                            }
                        }
                    }
                }
            }
        }


    }

    private static void ptSort(Point[] list) {
        for (int i = 0; i < list.length; i++) {
            int j = i;
            while ((j > 0) && less(list[j - 1], list[j])) {
                swap(list, j - 1, j);
                j--;
            }
        }
    }

    private static boolean less(Point a, Point b) {
        return a.compareTo(b) < 0;
    }

    private static void swap(Point[] list, int a, int b) {
        Point swap = list[a];
        list[a] = list[b];
        list[b] = swap;
    }

    private static LineSegment[] push(LineSegment[] list, LineSegment item) {
        LineSegment[] copy = new LineSegment[list.length + 1];
        System.arraycopy(list, 0, copy, 0, list.length);
        copy[list.length] = item;
        return copy;
    }


    /* ******************
        Requested API
    ****************** */
    public int numberOfSegments() {
        return segNum;
    }

    public LineSegment[] segments() {
        return temp;
    }

}
