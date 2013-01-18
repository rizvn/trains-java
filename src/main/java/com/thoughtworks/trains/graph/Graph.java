package com.thoughtworks.trains.graph;

import java.util.List;
import java.util.Set;

import com.thoughtworks.trains.exception.NoSuchRouteException;
import com.thoughtworks.trains.filter.PathFilter;

/**
 * Basic interface for a graph.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the vertex
 */
public interface Graph<V> {

    /**
     * Adds a new weighted edge to the graph. The source and destination vertex
     * need to be added to the graph before adding the edge
     * 
     * @param from
     *            Source vertex
     * @param to
     *            Destination vertex
     * @param weight
     *            Edge weight
     * @return true if the edge is added successfully or false otherwise
     * @throws IllegalArgumentException
     *             if either vertex is not added to the graph
     * @see #addVertex(Object)
     */
    boolean addEdge(V from, V to, int weight);

    /**
     * Adds a new vertex to the graph if it doesn't exist. The vertex can not be
     * null
     * 
     * @param vertex
     *            The new vertex to add
     * @return true if the vertex is added successfully
     * @throws IllegalArgumentException
     *             if the vertex is null
     */
    boolean addVertex(V vertex);

    /**
     * Gets an existing edge between two vertex if it exists or null in other
     * case
     * 
     * @param from
     *            Origin vertex
     * @param to
     *            Destination vertex
     * @return An {@link Edge} between the two vertex or null if it doesn't
     *         exist
     * @throws IllegalArgumentException
     *             if either vertex is not present in the graph
     */
    Edge<V> getEdge(V from, V to);

    /**
     * Returns a {@link Set} view of all the vertex added to this Graph
     * 
     * @return A set containing all the vertex
     */
    Set<V> getAllVertex();

    /**
     * Gets all the existing paths between two vertex that are valid given the
     * specified {@link PathFilter}. Depending on the filter this may include
     * possible cycles in the graph. So for example if the graph is
     * "A-->B-->C-->A", possible paths between A and C include "A-->B-->C",
     * "A-->B-->C-->A-->B-->C" and so on.
     * 
     * @param startingNode
     *            The source vertex
     * @param endingNode
     *            The destination vertex
     * @param filter
     *            The {@link PathFilter filter} used to determine the paths
     *            returned
     * @return A List of {@link GraphPath} representing each resulting path
     * @throws NoSuchRouteException
     *             if there is no path between the two vertex
     * @throws IllegalArgumentException
     *             if either node does not exist on the graph
     * @see PathFilter
     * @see GraphPath
     */
    List<Path<V>> getAllPaths(V startingNode, V endingNode, PathFilter<V> filter);

}
