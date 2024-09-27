import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraSP {
    private WeightedVertex[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private PriorityQueue<WeightedVertex> pq;    // priority queue of vertices


    public DijkstraSP(EdgeWeightedGraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new WeightedVertex[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        initializeDistTo(G);
        distTo[s].setWeight(0.0);

        initializePriorityQueue(G, s);
        relaxEdgesUntilQueueIsEmpty(G);

        assert check(G, s);
    }

    private void initializeDistTo(EdgeWeightedGraph G) {
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = new WeightedVertex(v, Double.POSITIVE_INFINITY);
        }
    }

    private void initializePriorityQueue(EdgeWeightedGraph G, int s) {
        pq = new PriorityQueue<>(G.V());
        pq.add(distTo[s]);
    }

    private void relaxEdgesUntilQueueIsEmpty(EdgeWeightedGraph G) {
        while (!pq.isEmpty()) {
            int v = pq.poll().index();
            for (DirectedEdge e : G.adjacent(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        double newWeight = distTo[v].weight() + e.weight();

        if (distTo[w].weight() > newWeight) {
            updateDistToAndEdgeTo(w, newWeight, e);
            updatePriorityQueue(w);
        }
    }

    private void updateDistToAndEdgeTo(int vertex, double newWeight, DirectedEdge edge) {
        distTo[vertex].setWeight(newWeight);
        edgeTo[vertex] = edge;
    }

    private void updatePriorityQueue(int vertex) {
        pq.remove(distTo[vertex]);
        pq.add(distTo[vertex]);
    }


    public double distTo(int v) {
        validateVertex(v);
        return distTo[v].weight();
    }


    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v].weight() < Double.POSITIVE_INFINITY;
    }


    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        Collections.reverse(path);
        return path;
    }
    private boolean check(EdgeWeightedGraph G, int s) {

        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        if (distTo[s].weight() != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v].weight() != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adjacent(v)) {
                int w = e.to();
                if (distTo[v].weight() + e.weight() < distTo[w].weight()) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v].weight() + e.weight() != distTo[w].weight()) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedGraph G = new EdgeWeightedUndigraph(in);


        DijkstraSP sp = new DijkstraSP(G, s);

        // print shortest paths
        if (!sp.hasPathTo(s)) {
            System.out.println("No path found");
        } else {
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)) {
                    System.out.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                    if (v != s) {
                        Stack<DirectedEdge> path = (Stack<DirectedEdge>) sp.pathTo(v);
                        while (!path.isEmpty()) {
                            DirectedEdge e = path.pop();
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
