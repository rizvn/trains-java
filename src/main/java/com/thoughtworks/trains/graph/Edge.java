package com.thoughtworks.trains.graph;

/**
 * Basic interface for a weighted edge between two vertex
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the source and destination vertex of this edge
 */
public interface Edge<V> {
    V getStartingVertex();

    V getEndingVertex();

    int getWeight();
}
