public class EdgeWeightedUndigraph extends EdgeWeightedGraph { //child class of EdgeWeightedGraph

    public EdgeWeightedUndigraph(int V) {
        super(V);
    }

    public EdgeWeightedUndigraph(In in) {
        super(in);
    }

    public EdgeWeightedUndigraph(EdgeWeightedGraph G) {
        super(G);
    }

    @Override //need to override to change the return type
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj.get(v).add(e);
        adj.get(w).add(e);
        E++;
    }



}
