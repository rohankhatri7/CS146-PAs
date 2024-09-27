/******************************************************************************
 *  Compilation:  javac BellmanFordSP.java
 *  Execution:    java BellmanFordSP filename.txt s
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Queue.java
 *                EdgeWeightedDirectedCycle.java
 *  Data files:   tinyEWDn.txt
 *                miniEWDnc.txt
 *                mediumEWD.txt
 *
 *
 *  Bellman-Ford shortest path algorithm. Computes the shortest path tree in
 *  edge-weighted digraph G from vertex s, or finds a negative cost cycle
 *  reachable from s.
 *
 *  % java BellmanFordSP tinyEWDn.txt 0
 *  0 to 0 ( 0.00)
 *  0 to 1 ( 0.93)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35   5->1  0.32
 *  0 to 2 ( 0.26)  0->2  0.26
 *  0 to 3 ( 0.99)  0->2  0.26   2->7  0.34   7->3  0.39
 *  0 to 4 ( 0.26)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25
 *  0 to 5 ( 0.61)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35
 *  0 to 6 ( 1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52
 *  0 to 7 ( 0.60)  0->2  0.26   2->7  0.34
 *
 *  % java BellmanFordSP tinyEWDnc.txt 0
 *  4->5  0.35
 *  5->4 -0.66
 *
 *
 ******************************************************************************/
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BellmanFordSP {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path

    /**
     * Computes a shortest paths tree from {@code s} to every other vertex in
     * the edge-weighted digraph {@code G}.
     * @param G the acyclic digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */

    public BellmanFordSP(EdgeWeightedGraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        //YOUR CODE HERE

        validateVertex(s);

        int totalVertices = G.V();
        for (int v = 0; v < totalVertices; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Iterable<DirectedEdge> edges = G.edges();
        int iterations = totalVertices - 1;
        for (int i = 1; i < iterations; i++) {
            for (DirectedEdge edge : edges) {
                relax(edge);
            }
        }

        G.edges().forEach(e -> {
            int v = e.from();
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                System.err.println("Negative cycle detected");
                //uncomment to throw the exception, but shortest paths will not be printed
               // throw new UnsupportedOperationException("Negative cycle detected");
            }
        });

        assert check(G, s);
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
    /**
     * Is there a path from the source {@code s} to vertex {@code v}?
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return Double.POSITIVE_INFINITY > distTo[v];
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws UnsupportedOperationException if there is a negative cost cycle reachable
     *         from the source vertex {@code s}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }
    /**
     * Returns a shortest path from the source {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return a shortest path from the source {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws UnsupportedOperationException if there is a negative cost cycle reachable
     *         from the source vertex {@code s}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public LinkedList<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        //YOUR CODE HERE
        if (!hasPathTo(v)) return null;
        LinkedList<DirectedEdge> path = new LinkedList<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            if (path.contains(e)) {
                System.out.println("Negative cycle detected");
                return null;
            }
            path.addFirst(e);
        }
        return path;
    }

    private boolean check(EdgeWeightedGraph G, int s) {
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }

        int totalVertices = G.V();
        for (int vertex = 0; vertex < totalVertices; vertex++) {
            if (vertex == s) continue;
            if (edgeTo[vertex] == null && distTo[vertex] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        for (int vertex = 0; vertex < totalVertices; vertex++) {
            for (DirectedEdge edge : G.adjacent(vertex)) {
                int destination = edge.to();
                if (distTo[vertex] + edge.weight() < distTo[destination]) {
                    System.err.println("edge " + edge + " not relaxed");
                    return false;
                }
            }
        }

        for (int destination = 0; destination < totalVertices; destination++) {
            if (edgeTo[destination] == null) continue;
            DirectedEdge edge = edgeTo[destination];
            int source = edge.from();
            if (destination != edge.to()) return false;
            if (distTo[source] + edge.weight() != distTo[destination]) {
                System.err.println("edge " + edge + " on is not on tightest shortest path");
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v >= V || v < 0)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);

        EdgeWeightedGraph G2 = new EdgeWeightedDigraph(in); //directed graph initialization
        BellmanFordSP sp = new BellmanFordSP(G2, s);


        if (!sp.hasPathTo(s)) {
            System.out.println("No path found");
        } else {
            for (int v = 0; v < G2.V(); v++) {
                if (sp.hasPathTo(v)) {
                    System.out.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                    if (v != s) {
                        for (DirectedEdge e : sp.pathTo(v)) {
                            System.out.printf("%d->%d %5.2f   ", e.from(), e.to(), e.weight());
                        }
                    }
                    System.out.println();
                } else {
                    System.out.printf("%d to %d           no path\n", s, v);
                }
            }
        }
    }
}
