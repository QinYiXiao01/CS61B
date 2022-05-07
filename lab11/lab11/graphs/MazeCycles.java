package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int[] nodeTo;
    private boolean isFound;

    public MazeCycles(Maze m) {
        super(m);
        this.maze = m;
        s = maze.xyTo1D(1, 1);
        distTo[s] = 0;
        edgeTo[s] = s;
        nodeTo = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        search(-1, s);
    }

    // Helper methods go here
    private void search(int g, int v) { // 这里引入g 代表着爷爷
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                nodeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                search(v, w);
            }
            else if (w != g) { // 如果父结点v的儿子结点w已经被访问过且不是爷爷结点g，就说明存在圈
                //edgeTo[w] = v;
                for (int i = v; i != w; i = nodeTo[i]) {
                    edgeTo[i] = nodeTo[i];
                    announce();
                }
                isFound = true;
            }
            if (isFound) return;
        }
    }
}

