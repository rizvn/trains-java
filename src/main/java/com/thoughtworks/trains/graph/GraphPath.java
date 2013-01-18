package com.thoughtworks.trains.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphPath<V> implements Path<V> {

    private final List<Edge<V>> edgeList = new ArrayList<Edge<V>>();
    private int totalWeight = 0;

    /**
     * Private constructor to ensure that {@link #emptyPath()} is used to
     * construct instances of this class
     */
    private GraphPath() {
    }

    /**
     * Private constructor to ensure that {@link #copyPath(Path)} is used to
     * construct instances of this class
     */
    private GraphPath(final Path<V> otherPath) {
        edgeList.addAll(otherPath.getEdgeList());
        this.totalWeight = otherPath.getPathTotalWeight();
    }

    /**
     * Static factory method to construct empty instances of this class. Static
     * factory methods reduce the verbosity of creating parameterized type
     * instances because they provide <a href=
     * "http://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html"
     * > type inference</a>. Also, the fact that they can be named as a regular
     * method makes them more meaningful that a constructor
     * 
     * @return An empty path
     */
    public static <V> Path<V> emptyPath() {
        return new GraphPath<V>();
    }

    /**
     * Static factory method that return a copy of the path passed as parameter
     * 
     * @param otherPath
     *            The path to copy from
     * @return A new path equal to otherPath
     */
    public static <V> Path<V> copyPath(final Path<V> otherPath) {
        return new GraphPath<V>(otherPath);
    }

    @Override
    public void addEdge(final Edge<V> edge) {
        if (!edgeIsConsecutive(edge)) {
            throw new IllegalArgumentException("The edge " + edge + " is not consecutive to the existing path");
        }
        edgeList.add(edge);
        totalWeight += edge.getWeight();
    }

    private boolean edgeIsConsecutive(final Edge<V> edge) {
        final V lastNode = getLastNode();
        if (lastNode != null && !lastNode.equals(edge.getStartingVertex())) {
            return false;
        }
        return true;
    }

    @Override
    public int getPathTotalWeight() {
        return totalWeight;
    }

    @Override
    public int getNumberOfHops() {
        return edgeList.size();
    }

    @Override
    public V getLastNode() {
        V node = null;
        if (!edgeList.isEmpty()) {
            node = edgeList.get(edgeList.size() - 1).getEndingVertex();
        }
        return node;
    }

    @Override
    public void removeLastEdge() {
        if (!edgeList.isEmpty()) {
            final Edge<V> lastEdge = edgeList.get(edgeList.size() - 1);
            this.totalWeight -= lastEdge.getWeight();
            edgeList.remove(edgeList.size() - 1);
        }
    }

    @Override
    public List<Edge<V>> getEdgeList() {
        return Collections.unmodifiableList(edgeList);
    }

    @Override
    public boolean hasRepeatedEdges() {
        for (int i = 0; i < edgeList.size(); i++) {
            for (int j = i + 1; j < edgeList.size(); j++) {
                if (edgeList.get(i).equals(edgeList.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean startsWith(final Path<V> otherPath) {
        final List<Edge<V>> partialPath = otherPath.getEdgeList();
        final List<Edge<V>> completePath = getEdgeList();
        for (int i = 0; i < partialPath.size(); i++) {
            if (i >= completePath.size() || !partialPath.get(i).equals(completePath.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraphPath (" + totalWeight + ") [edgeList=" + edgeList + "]";
    }

    @Override
    public int compareTo(final Path<V> otherPath) {
        return this.getPathTotalWeight() - otherPath.getPathTotalWeight();
    }

}
