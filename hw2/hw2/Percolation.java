package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

import java.util.HashSet;

public class Percolation {
    private WeightedQuickUnionUF percSet;
    private int lineLength;
    private HashSet<Integer> openSite;

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
     * 将相邻的格子连通
     */
    public void union(int newlyOpen) {
        for (int site : openSite) {
            int sub = Math.abs(site - newlyOpen);
            // 如果现有的格子序号和新打开的格子序号相差1格或1行，就连通
            if (sub == 1 || sub == lineLength) {
                percSet.union(site, newlyOpen);
            }
        }
    }

    /**
     * 将打开的格子加到set里
     * @param row
     * @param col
     */
    public void open(int row, int col){
        int newlyOpen = locate(row, col);
        openSite.add(newlyOpen);
        union(newlyOpen);
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

    /**
     * 从上面通下来，相当于灌水，联通的空格子都是full
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col){
        // 如果这个格子是和第一行联通的，就说明他满了
        for (int site : openSite) {
            if (site < lineLength) {
                return percSet.connected(locate(row, col), site);
            }
        }
        return false;
    }

    public boolean isFull(int targetSite){
        // 如果这个格子是和第一行联通的，就说明他满了
        for (int site : openSite) {
            if (site < lineLength) {
                return percSet.connected(targetSite, site);
            }
        }
        return false;
    }

    /**
     * 返回open格子的个数
     * @return
     */
    public int numberOfOpenSites(){
        return openSite.size();
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates(){
        // 如果最后一行的open格子满了，就说明联通了
        for (int site : openSite) {
            if (site >= lineLength * (lineLength - 1) && site <= lineLength * lineLength) {
                return isFull(site);
            }
        }
        return false;
    }

    public static void main(String[] args){}   // use for unit testing (not required)

}
