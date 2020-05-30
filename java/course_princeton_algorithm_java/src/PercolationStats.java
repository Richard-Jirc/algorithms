import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        results = new double[trials];
        int i = 1;
        while (i <= trials) {
            Percolation grid = new Percolation(n);
            do {
                int[] nextToOpen = random(grid, n);
                grid.open(nextToOpen[0], nextToOpen[1]);
            } while (!grid.percolates());
            results[i - 1] = (double) grid.numberOfOpenSites() / (n * n);
            i++;
        }

    }
    public double mean() {
        return StdStats.mean(this.results);
    }
    public double stddev() {
        return StdStats.stddev(this.results);
    }
    public double confidenceLo() {
        return this.mean() - this.interval();
    }
    public double confidenceHi() {
        return this.mean() + this.interval();
    }
    private double interval() {
        return 1.96 * this.stddev() / Math.sqrt(this.results.length);
    }

    private static int[] random(Percolation grid, int size) {
        int x, y;
        do {
            x = StdRandom.uniform(1, size + 1);
            y = StdRandom.uniform(1, size + 1);
        } while (grid.isOpen(x, y));
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(20, 100);
        System.out.println(test.mean());
    }
}
