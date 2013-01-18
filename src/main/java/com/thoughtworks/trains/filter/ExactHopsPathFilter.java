package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.graph.Path;

/**
 * This filter will check to see if the given path has exactly the amount of
 * hops, i.e., edges, as the value specified on its constructor
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class ExactHopsPathFilter<V> implements PathFilter<V> {
    private final int hopsNumber;

    public ExactHopsPathFilter(final int hopsNumber) {
        super();
        this.hopsNumber = hopsNumber;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getNumberOfHops() == hopsNumber;
    }

}
