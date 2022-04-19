package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int totalTime;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        // T可以理解为进行几次试验
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N & T 应该大于0");
        }
        this.totalTime = T;
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            while (!perc.percolates()) {
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }
            }
            // fraction为open的格子所占比例
            double fraction = perc.numberOfOpenSites() / N * N;
            fractions[i] = fraction;
        }

    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - (1.96 * sigma / Math.sqrt(totalTime));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + (1.96 * sigma / Math.sqrt(totalTime));
    }
}
