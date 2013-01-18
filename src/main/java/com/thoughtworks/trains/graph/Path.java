package com.thoughtworks.trains.graph;

import java.util.List;

/**
 * General interface that represents a path in a graph. In this context, a path
 * is defined as a series of consecutive edges.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the vertex in this path
 */
public interface Path<V> extends Comparable<Path<V>> {

    /**
     * Adds a new edge to the path. This method should check that the added edge
     * is consecutive to the previous. This means that if the current path is
     * "A-->B", the new edge should have "B" as its starting vertex
     * 
     * @param edge
     *            The new edge to be added to the path
     * @throws IllegalArgumentException
     *             if the edge is not consecutive to the previous
     */
    void addEdge(Edge<V> edge);

    /**
     * Computes the total weight of this path, i.e., the sum of the weight of
     * each individual edge on this path
     * 
     * @return The total weight of this path
     */
    int getPathTotalWeight();

    /**
     * Computes the total number of hops or edges on this path
     * 
     * @return Total number of hops
     */
    int getNumberOfHops();

    /**
     * Returns the last node on this path. This is the ending node of the last
     * edge. So for instance, on a path "A-->B-->C" the last node would be C
     * 
     * @return The last node of this path
     */
    V getLastNode();

    /**
     * Removes the last edge contained in this path
     */
    void removeLastEdge();

    /**
     * Returns a List view of the edges on this path or an empty list if the
     * current path is empty
     * 
     * @return List view of this path's edges
     */
    List<Edge<V>> getEdgeList();

    /**
     * Determines whether or not this path contains any duplicate edge.
     * 
     * @return true if the path has two equivalent edges, false otherwise
     */
    boolean hasRepeatedEdges();

    /**
     * Determines if this path starts with the same edges as the path passed as
     * parameter. A path X starts with path Y if all edges of Y are present in X
     * in the same order from the beginning. So for example the path
     * "A-->B-->C-->D" starts with "A--B--C" but does not start with "A-->C"
     * 
     * @param otherPath
     *            The contained path
     * @return true if this path starts with the one passed as parameter
     */
    boolean startsWith(Path<V> otherPath);

}