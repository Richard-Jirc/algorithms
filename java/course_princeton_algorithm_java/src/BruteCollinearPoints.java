import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final Stack<LineSegment> result;
    private final LineSegment[] finalResult;
    private int count;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("array null");

        result = new Stack<>();
        count = 0;

        Point[] array = new Point[points.length]; // defensive copy of the array to compute.
        Point[] temp = new Point[4];              // arbitrarily checking four points at a time.

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("element null"); // constructing defensive copy while checking null.
            array[i] = points[i];
        }

        for (int j = 0; j < array.length - 1; j++) {
            for (int k = j + 1; k < array.length; k++) {
                if (array[j].compareTo(array[k]) == 0) throw new IllegalArgumentException("duplicate point"); // o(n^2) time to check duplicate points.
            }
        }

        for (int p = 0; p < array.length - 3; p++) {
            for (int q = p + 1; q < array.length - 2; q++) {
                for (int r = q + 1; r < array.length -1; r++) {
                    for (int s = r + 1; s < array.length; s++) {
                        temp[0] = array[p];
                        temp[1] = array[q];
                        temp[2] = array[r];
                        temp[3] = array[s];
                        analyze(temp); // o(n^4) time to analyze four points at a time.
                    }
                }
            }
        }
        finalResult = new LineSegment[result.size()];
        for (int i = 0; i < count; i++) {
            finalResult[i] = result.pop();
        }
    }

    private void analyze(Point[] list) {
        Arrays.sort(list);
        double longest = list[0].slopeTo(list[3]);
        for (int i = 1; i < 3; i++) {
            double current = list[0].slopeTo(list[i]);
            if (current != longest) return;
        }
        result.push(new LineSegment(list[0], list[3]));
        count++;
    }

    public int numberOfSegments() { return count; }

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[finalResult.length];
        System.arraycopy(finalResult, 0, copy, 0, finalResult.length);
        return copy;
    }


    public static void main(String[] args) {
        Point o1 = new Point(0, 0);
        Point o2 = new Point(2, 0);
        Point o3 = new Point(4, 0);
        Point o4 = new Point(6, 0);
        Point o5 = new Point(-1, -1);
        Point o6 = new Point(2, 2);
        Point o7 = new Point(-3, -3);
        Point[] test = {o1, o2, o3, o4, o5, o6, o7};
        BruteCollinearPoints findIt = new BruteCollinearPoints(test);
        for (LineSegment i : findIt.segments()) {
            System.out.println(i);
        }
    }

}
