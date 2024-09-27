/******************************************************************************
 *  Compilation:  javac BreadthFirstDirectedPaths.java
 *  Execution:    java BreadthFirstDirectedPaths digraph.txt s
 *  Dependencies: Digraph.java Use any Stack/Queue as needed
 *  Run breadth-first search on a digraph.
 *  Runs in O(E + V) time.
 *
 *  % java BreadthFirstDirectedPaths tinyDG.txt 3
 *  3 to 0 (2):  3->2->0
 *  3 to 1 (3):  3->2->0->1
 *  3 to 2 (1):  3->2
 *  3 to 3 (0):  3
 *  3 to 4 (2):  3->5->4
 *  3 to 5 (1):  3->5
 *  3 to 6 (-):  not connected
 *  3 to 7 (-):  not connected
 *  3 to 8 (-):  not connected
 *  3 to 9 (-):  not connected
 *  3 to 10 (-):  not connected
 *  3 to 11 (-):  not connected
 *  3 to 12 (-):  not connected
 *
 ******************************************************************************/
import java.util.*;

public class BreadthFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s->v path?
    private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo;      // distTo[v] = length of shortest s->v path

    /**
     * Computes the shortest path from {@code s} and every other vertex in graph {@code G}.
     * @param G the digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public BreadthFirstDirectedPaths(Digraph G, int s) {
        //YOUR CODE HERE
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertex(s);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        distTo[s] = 0;
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adjacent(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    /**
     * Is there a directed path from the source {@code s} (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path from the source {@code s}
     * (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in such a shortest path
     *         (or {@code Integer.MAX_VALUE} if there is no such path)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path from {@code s} (or sources) to {@code v}, or
     * {@code null} if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        StringBuilder path = new StringBuilder();
        Stack<Integer> pathStack = new Stack<>();
        int x = v;
        while (distTo[x] != 0) {
            pathStack.push(x);
            x = edgeTo[x];
        }
        pathStack.push(x);
        while (!pathStack.isEmpty()) {
            path.append(pathStack.pop());
            if (!pathStack.isEmpty()) path.append("->");
        }
        return path.toString();
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) { //checks if vertex is valid
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not in the range of 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code BreadthFirstDirectedPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        System.out.println(G);

        int s = Integer.parseInt(args[1]);
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                System.out.println(bfs.pathTo(v));
                System.out.println();
            }

            else {
                System.out.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }


}
