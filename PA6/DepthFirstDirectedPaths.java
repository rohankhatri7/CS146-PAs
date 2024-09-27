/******************************************************************************
 *  Compilation:  javac DepthFirstDirectedPaths.java
 *  Execution:    java DepthFirstDirectedPaths digraph.txt s
 *  Dependencies: Digraph.java Use any Stack/Queue implementation.
 *  Determine reachability in a digraph from a given vertex using
 *  depth-first search.
 *  Runs in O(E + V) time.
 *
 *  % java DepthFirstDirectedPaths tinyDG.txt 3
 *  3 to 0:  3-5-4-2-0
 *  3 to 1:  3-5-4-2-0-1
 *  3 to 2:  3-5-4-2
 *  3 to 3:  3
 *  3 to 4:  3-5-4
 *  3 to 5:  3-5
 *  3 to 6:  not connected
 *  3 to 7:  not connected
 *  3 to 8:  not connected
 *  3 to 9:  not connected
 *  3 to 10:  not connected
 *  3 to 11:  not connected
 *  3 to 12:  not connected
 *
 ******************************************************************************/
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;

public class DepthFirstDirectedPaths {
    private boolean[] marked;  // marked[v] = true iff v is reachable from s
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int s;       // source vertex

    /**
     * Computes a directed path from {@code s} to every other vertex in digraph {@code G}.
     * @param  G the digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        //YOUR CODE HERE
        marked[v] = true;
        for (int w : G.adjacent(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a directed path from the source vertex {@code s} to vertex {@code v}?
     * @param  v the vertex
     * @return {@code true} if there is a directed path from the source
     *         vertex {@code s} to vertex {@code v}, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }


    /**
     * Returns a directed path from the source vertex {@code s} to vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a directed path from the source vertex
     *         {@code s} to vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        int x = v;
        while (x != s) {
            path.push(x);
            x = edgeTo[x];
        }
        path.push(s);

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop());
            if (!path.isEmpty())
                sb.append("-");
        }
        return sb.toString();
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v >= V || v < 0)
            throw new IllegalArgumentException("vertex " + v + " is not in the range of 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DepthFirstDirectedPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        System.out.println(G);

        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.printf("%d to %d:  ", s, v);
                System.out.println(dfs.pathTo(v));
                //System.out.println();
            }

            else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
