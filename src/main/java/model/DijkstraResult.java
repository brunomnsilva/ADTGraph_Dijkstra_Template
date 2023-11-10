package model;

import com.brunomnsilva.smartgraph.graph.Vertex;

import java.util.*;

/**
 * This class holds a result from running the Dijkstra algorithm
 * over an ADT Graph model. It assumes the result is correctly
 * calculated.
 *
 * @param <V> the vertex element type
 * @param <E> the edge element type
 */
public class DijkstraResult<V, E> {

    private Vertex<V> origin;
    private Map<Vertex<V>, Double> costs;
    private Map<Vertex<V>, Vertex<V>> predecessors;

    public DijkstraResult(Vertex<V> origin, Map<Vertex<V>, Double> costs, Map<Vertex<V>, Vertex<V>> predecessors) {
        if(origin == null || costs == null || predecessors == null) {
            throw new IllegalArgumentException("Initializers cannot be null");
        }
        if(costs.size() == 0 || predecessors.size() == 0) {
            throw new IllegalArgumentException("Costs or predecessors cannot be empty.");
        }

        this.origin = origin;
        this.costs = costs;
        this.predecessors = predecessors;
    }

    /**
     * The origin vertex for the current Dijkstra result.
     * @return the origin vertex
     */
    public Vertex<V> getOrigin() {
        return origin;
    }

    /**
     * Returns the minimum cost to <i>destination</i>
     * @param destination destination vertex
     * @return the minimum cost
     * @throws NoPathException if there is no path to <i>destination</i>
     */
    public double getMinimumCostTo(Vertex<V> destination)
                                    throws NoPathException {
        verifyPathExistence(destination);

        return costs.get(destination);
    }

    /**
     * Returns the minimum cost path to <i>destination</i>.
     * @param destination destination vertex
     * @return the minimum cost path
     * @throws NoPathException if there is no path to <i>destination</i>
     */
    public Collection<Vertex<V>> getMinimumCostPathTo(Vertex<V> destination)
                                    throws NoPathException {
        verifyPathExistence(destination);

        List<Vertex<V>> path = new ArrayList<>();

        // TODO: extract path from predecessors

        return path;
    }

    private void verifyPathExistence(Vertex<V> destination)
                                throws NoPathException {
        // If there is no predecessor assigned to 'destination', then
        // there is surely no path to it.
        if(predecessors.get(destination) == null) {
            throw new NoPathException(destination.element().toString());
        }
    }

    @Override
    public String toString() {
        // Prints result in a tabular format
        List<Vertex<V>> vertices = new ArrayList<>( costs.keySet() );
        Collections.sort(vertices, Comparator.comparing(v -> v.element().toString()));

        StringBuilder sb = new StringBuilder();

        String header = String.format("%15s | %15s | %15s\n", "Vertex", "Cost", "Predecessor");
        sb.append(header);
        for (Vertex<V> v : vertices) {
            double cost = costs.get(v);
            Vertex<V> w = predecessors.get(v);

            String wStr = (w != null) ? w.element().toString() : "<null>";
            String costStr = (cost != Double.MAX_VALUE) ? String.format("%.2f", cost) : "∞";

            String line = String.format("%15s | %15s | %15s\n",
                    v.element().toString(), costStr, wStr);

            sb.append(line);
        }

        return sb.toString();
    }
}
