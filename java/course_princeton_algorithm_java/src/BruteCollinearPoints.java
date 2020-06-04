import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final Stack<LineSegment> result;
    private final LineSegment[] finalResult;
    private int count;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        result = new Stack<>();
        Point[] temp = new Point[4];
        count = 0;
        Arrays.sort(points);
        if (points.length == 1) {
            if (points[0] == null) throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = 1; j < points.length; j++) {
                    if (points[i] == null || points[j] == null || points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
                }
            }
        }
        for (int p = 0; p < points.length - 3; p++) {
            temp[0] = points[p];
            for (int q = p + 1; q < points.length - 2; q++) {
                temp[1] = points[q];
                for (int r = q + 1; r < points.length -1; r++) {
                    temp[2] = points[r];
                    for (int s = r + 1; s < points.length; s++) {
                        temp[3] = points[s];
                        analyze(temp);
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
        Point[] test = {o1, o2, o3, o4, o5, o6};
        BruteCollinearPoints findIt = new BruteCollinearPoints(test);
        for (LineSegment i : findIt.segments()) {
            System.out.println(i);
        }
    }

}
