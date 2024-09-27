public class EdgeWeightedDigraph extends EdgeWeightedGraph { //child class of EdgeWeightedGraph


    public EdgeWeightedDigraph(int V) {
        super(V);
    }

    public EdgeWeightedDigraph(In in) {
        super(in);
    }

    public EdgeWeightedDigraph(EdgeWeightedGraph G) {
        super(G);
    }

    @Override //need to override to change the return type
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj.get(v).add(e);
        indegree[w]++;
        E++;
    }

}

