package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    private ArrayList<WorldState> solution;
    private int moves;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int priority;
        private SearchNode prev;

        // 结点中存储WorldState而不是一个String
        public SearchNode(WorldState state, int step, SearchNode prev) {
            this.state = state;
            this.priority = step + state.estimatedDistanceToGoal();
            this.prev = prev;
        }

        public WorldState getState() {
            return state;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.getPriority() - o.getPriority();
        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     *
     * @param initial
     */
    public Solver(WorldState initial) {
        solution = new ArrayList<>();
        // 种一棵树
        MinPQ<SearchNode> sequence = new MinPQ<>();
        // 将祖宗结点放入树中
        SearchNode prev = null;
        int step = 0;
        sequence.insert(new SearchNode(initial, step, prev));

        while (true) {
            SearchNode current = sequence.delMin();
            solution.add(current.getState());
            prev = current;
            step++;
            if (current.state.isGoal()) {
                this.moves = step;
                return;
            }
            // 加入当前结点状态的邻居们
            for (WorldState neighbor : current.state.neighbors()) {
                if (!neighbor.equals(current.prev.state)) {
                    sequence.insert(new SearchNode(neighbor, step, prev));
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     *
     * @return
     */
    public int moves() {
        return this.moves;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     *
     * @return
     */
    public Iterable<WorldState> solution() {
        return this.solution;
    }
}
