/******************************************************************************
 *  Compilation:  javac WeightedVertex.java
 *  Execution:    java WeightedVertex
 *  Dependencies: 
 *
 *  Immutable weighted vertex. Useful to run shortest path and other algorithms
 *
 ******************************************************************************/

public class WeightedVertex implements Comparable<WeightedVertex> {
    private final int v;
    private double weight;

    /**
     * Initializes a weighted vertex at index {@code v} with
     * the given {@code weight}.
     * @param v the index of the vertex
     * @param weight the weight of the weighted vertex
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public WeightedVertex(int v, double weight) {
        this.v = v;
        this.weight = weight;
    }

    /**
     * Returns the weight of the vertex.
     * @return the weight of the vertex
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns the index of the vertex.
     * @return the index of the vertex
     */

    public int index() {
        return v;
    }

    /**
     * Set the weight of the vertex.
     * @return
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }


    /**
     * Returns a string representation of the vertex.
     * @return a string representation of the vertex
     */
    public String toString() {
        return v + " " + String.format("%5.2f", weight);
    }

    public int compareTo(WeightedVertex that) {
        return Double.compare(this.weight(), that.weight());
    }

    /**
     * Unit tests the {@code WeightedVertex} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        WeightedVertex e = new WeightedVertex(12, 5.67);
        System.out.println(e);
    }
}
