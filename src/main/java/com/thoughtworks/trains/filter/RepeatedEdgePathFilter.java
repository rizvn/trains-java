package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.graph.Path;

/**
 * This filter will return true if and only if the given path contains no
 * repeated edges.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class RepeatedEdgePathFilter<V> implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return !path.hasRepeatedEdges();
    }

}
