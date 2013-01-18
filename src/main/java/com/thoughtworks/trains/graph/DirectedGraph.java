package com.thoughtworks.trains.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.trains.exception.NoSuchRouteException;
import com.thoughtworks.trains.filter.PathFilter;
/**
 * Default graph implementation. This is by no means a complete
 * implementation. It just provides the basic methods to solve 
 * the given problem. However, given the fact that only the {@link Graph}
 * interface is used throughout the application, it should be 
 * fairly easy to provide a more complete implementation.
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 *
 * @param <V>
 */
public class DirectedGraph<V> implements Graph<V> {
    private final Map<V, Set<Edge<V>>> edges = new HashMap<V, Set<Edge<V>>>();

    @Override
    public boolean addVertex(final V vertex) {
        assertVertexNotNull(vertex);
        if (!edges.containsKey(vertex)) {
            edges.put(vertex, new LinkedHashSet<Edge<V>>());
            return true;
        }
        return false;
    }

    private void assertVertexNotNull(final V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex can not be null");
        }
    }

    @Override
    public boolean addEdge(final V from, final V to, final int weight) {
        assertVertexExists(from);
        assertVertexExists(to);

        final Edge<V> newEdge = DefaultEdge.getWeightedEdge(from, to, weight);

        final Set<Edge<V>> sourceEdges = edges.get(from);
        // No need for null check because we already
        // checked that the two vertex exist in the graph
        if (sourceEdges.contains(newEdge)) {
            // If the graph already contains the edge, remove it
            // and add the new one
            sourceEdges.remove(newEdge);
        }
        return edges.get(from).add(newEdge);
    }

    private void assertVertexExists(final V vertex) {
        if (!edges.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex.toString() + " does not exist");
        }
    }

    @Override
    public Edge<V> getEdge(final V from, final V to) {
        assertVertexExists(from);
        assertVertexExists(to);

        final Set<Edge<V>> startingVertexEdges = edges.get(from);
        for (final Edge<V> eachEdge : startingVertexEdges) {
            if (eachEdge.getEndingVertex().equals(to)) {
                return eachEdge;
            }
        }
        return null;
    }

    @Override
    public List<Path<V>> getAllPaths(final V startingNode, final V endingNode, final PathFilter<V> filter) {
        assertVertexExists(startingNode);
        assertVertexExists(endingNode);

        final List<Path<V>> paths = new ArrayList<Path<V>>();
        for (final Edge<V> each : edges.get(startingNode)) {
            final Path<V> path = GraphPath.emptyPath();
            path.addEdge(each);
            paths.addAll(search(path, filter, endingNode));
        }

        if (paths.isEmpty()) {
            throw new NoSuchRouteException(startingNode.toString(), endingNode.toString());
        }
        return paths;
    }

    // Basically a Depth First search without keeping tack of visited nodes,
    // since here we don't want
    // to avoid cycles. Also known as Depth-limited search, using the filter to
    // stop the recursive
    // calls as soon as the current path is determined to be invalid
    private List<Path<V>> search(final Path<V> path, final PathFilter<V> filter, final V end) {
        final List<Path<V>> paths = new ArrayList<Path<V>>();
        if (filter.passFilter(path)) {
            if (hasReachedGoal(path, end)) {
                paths.add(GraphPath.copyPath(path));
            }
            for (final Edge<V> each : edges.get(path.getLastNode())) {
                path.addEdge(each);
                paths.addAll(search(path, filter, end));
            }

        }
        path.removeLastEdge();
        return paths;
    }

    private boolean hasReachedGoal(final Path<V> path, final V end) {
        return path.getLastNode().equals(end);
    }

    @Override
    public Set<V> getAllVertex() {
        return Collections.unmodifiableSet(edges.keySet());
    }

}
