package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.graph.Path;

/**
 * This filter will check to see if the number of hops or edges on the given
 * path is less than or equal to the value specified on the constructor
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class MaxHopsPathFilter<V> implements PathFilter<V> {
    private final int maxHops;

    public MaxHopsPathFilter(final int maxHops) {
        super();
        this.maxHops = maxHops;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getNumberOfHops() <= maxHops;
    }

}
