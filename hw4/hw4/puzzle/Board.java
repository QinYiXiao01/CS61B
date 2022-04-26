package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[][] board;
    private int N;
    private static final int BLANK = 0;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles) {
        N = tiles[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i > N || j > N) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    /**
     * 返回N
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board
     * @return
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int N = size();
        // 找到空格元素的位置(a, b)
        int a = -1;
        int b = -1;
        for (int rug = 0; rug < N; rug++) {
            for (int tug = 0; tug < N; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    a = rug;
                    b = tug;
                }
            }
        }

        // 将初始Board复制一份
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = tileAt(i, j);
            }
        }

        // 遍历copy 若出现i或j一者为0一者为1时(相邻) 则和他的邻居交换 并置其为空
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(i - a) + Math.abs(j - b) - 1 == 0) {
                    copy[a][b] = copy[i][j];
                    copy[i][j] = BLANK;
                    Board neighbor = new Board(copy);
                    neighbors.enqueue(neighbor);
                    copy[i][j] = copy[a][b];
                    copy[a][b] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * 计算在错误位置的tile数量
     * @return
     */
    public int hamming() {
        int moves = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                else if (tileAt(i, j) != i * N + j + 1) {
                    moves++;
                }
            }
        }
        return moves;
    }

    /**
     * 计算错误位置到正确位置之和，总是比较大
     * @return
     */
    public int manhattan() {
        int moves = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int correctValue = i * N + j + 1;
                if (tileAt(i, j) == 0) {
                    continue;
                }
                else if (tileAt(i, j) != correctValue) {
                    moves += Math.abs(tileAt(i, j) / N + tileAt(i, j) % N - correctValue);
                }
            }
        }
        return moves;
    }

    /**
     * 重写的方法
     * @return
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        Board other = (Board)y;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (other.tileAt(i, j) != this.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
