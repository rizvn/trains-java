package com.thoughtworks.trains.graph;

/**
 * Default implementation of {@link Edge}
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the source and destination vertex of this edge
 */
public class DefaultEdge<V> implements Edge<V> {
    private final V startingVertex;
    private final V endingVertex;
    private final int weight;

    /**
     * Private constructor to ensure that
     * {@link #getWeightedEdge(Object, Object, int)} is used to construct
     * instances of this class
     */
    private DefaultEdge(final V startingVertex, final V endingVertex, final int weight) {
        super();
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
        this.weight = weight;
    }

    /**
     * Static factory method to construct instances of this class. Static
     * factory methods reduce the verbosity of creating parameterized type
     * instances because they provide <a href=
     * "http://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html"
     * > type inference</a>.
     * 
     * @param startingVertex
     *            The source vertex
     * @param endingVertex
     *            The destination vertex
     * @param weight
     *            The edge's weight
     * @return An {@link Edge} between the two vertex
     */
    public static <V> Edge<V> getWeightedEdge(final V startingVertex, final V endingVertex, final int weight) {
        return new DefaultEdge<V>(startingVertex, endingVertex, weight);
    }

    @Override
    public V getStartingVertex() {
        return startingVertex;
    }

    @Override
    public V getEndingVertex() {
        return endingVertex;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + startingVertex.toString() + ", " + endingVertex.toString() + "); ";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (endingVertex == null ? 0 : endingVertex.hashCode());
        result = prime * result + (startingVertex == null ? 0 : startingVertex.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    /**
     * Since two or more edges between the same vertex are not allowed for this particular implementation
     * 2 edges are considered equal if they have the same source and destination. The weight is not
     * taken into account
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultEdge other = (DefaultEdge) obj;
        if (endingVertex == null) {
            if (other.endingVertex != null) {
                return false;
            }
        } else if (!endingVertex.equals(other.endingVertex)) {
            return false;
        }
        if (startingVertex == null) {
            if (other.startingVertex != null) {
                return false;
            }
        } else if (!startingVertex.equals(other.startingVertex)) {
            return false;
        }
        return true;
    }

}
