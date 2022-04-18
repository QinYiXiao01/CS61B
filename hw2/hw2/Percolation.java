package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;

public class Percolation {
    private WeightedQuickUnionUF percSet;
    private int lineLength;
    private HashSet openSite;

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N){
        this.lineLength = N;
        openSite = new HashSet();
        // 每N个元素分1row
        percSet = new WeightedQuickUnionUF(N * N);
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col){
        openSite.add(locate(row, col));
    }

    /**
     * 根据行数和列数将元素在percSet中定位
     * @param row
     * @param col
     */
    public int locate(int row, int col) {
        return row * lineLength + col;
    }

    /**
     * is the site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col){
        return openSite.contains(locate(row, col));
    }
    public boolean isFull(int row, int col){}  // is the site (row, col) full?
    public int numberOfOpenSites(){}           // number of open sites
    public boolean percolates(){}              // does the system percolate?
    public static void main(String[] args){}   // use for unit testing (not required)

}
