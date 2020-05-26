/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] threshold;
    private final int times;
    private final double CONFID = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Stats Create Error");
        }

        times = trials;
        threshold = new double[trials];

        // initiate trials
        for (int i = 0; i < trials; i++) {

            Percolation test = new Percolation(n);

            do {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                test.open(row, col);
            } while (!test.percolates());

            double breakPt = test.numberOfOpenSites();
            // System.out.println(breakPt);
            threshold[i] = breakPt / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(this.threshold);
    }

    public double stddev() {
        return StdStats.stddev(this.threshold);
    }

    public double confidenceLo() {
        return this.mean() - (CONFID * this.stddev()) / Math.sqrt(this.times);
    }

    public double confidenceHi() {
        return this.mean() + (CONFID * this.stddev()) / Math.sqrt(this.times);
    }

    public static void main(String[] args) {

        PercolationStats wow = new PercolationStats(400, 100);

        // for (int i = 0; i < wow.threshold.length; i++) {
        //     System.out.print(wow.threshold[i] + ", ");
        // }

        System.out.println(wow.mean());
        System.out.println(wow.stddev());
        System.out.println("[" + wow.confidenceLo() + ", " + wow.confidenceHi() + "]");

    }
}
