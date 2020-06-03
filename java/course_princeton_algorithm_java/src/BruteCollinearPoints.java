import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints {
    private final Stack<LineSegment> result;
    private int count;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        result = new Stack<>();
        Point[] temp = new Point[4];
        count = 0;
        Arrays.sort(points);
        for (int p = 0; p < points.length - 3; p++) {
            temp[0] = points[p];
            for (int q = p + 1; q < points.length - 2; q++) {
                temp[1] = points[q];
                for (int r = q + 1; r < points.length -1; r++) {
                    temp[2] = points[r];
                    for (int s = r + 1; s < points.length; s++) {
                        temp[3] = points[s];
//                        for (Point i : temp) {
//                            System.out.print(i.toString() + "/ ");
//                        }
//                        System.out.println();
                        analyze(temp);
                    }
                }
            }
        }
    }

    private void analyze(Point[] list) {
        Arrays.sort(list);
        double longest = list[3].slopeTo(list[0]);
        boolean check = longest == list[3].slopeTo(list[1]) && longest == list[3].slopeTo(list[2]);
        if (check) {
            result.push(new LineSegment(list[0], list[3]));
            count++;
        }
    }

    public int numberOfSegments() {
        return count;
    }
    public LineSegment[] segments() {
        LineSegment[] finalResult = new LineSegment[result.size()];
        for (LineSegment seg : finalResult) {
            seg = result.pop();
        }
        return finalResult;
    }
    public static void main(String[] args) {
        Point o1 = new Point(0, 0);
        Point o2 = new Point(2, 0);
        Point o3 = new Point(4, 0);
        Point o4 = new Point(6, 0);
        Point o5 = new Point(-1, -1);
        Point o6 = new Point(2, 2);
        Point o7 = new Point(-2, -2);
        Point o8 = new Point(-3, -3);
        Point[] test = {o1, o2, o3, o4, o5, o6, o7, o8};
        BruteCollinearPoints Do = new BruteCollinearPoints(test);
        System.out.println(Do.numberOfSegments());
    }

}
