package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.graph.Path;

/**
 * This filter will check to see if the total weight of the given path is less
 * than the value specified on the constructor
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class WeightPathFilter<V> implements PathFilter<V> {
    private final int maxWeight;

    public WeightPathFilter(final int maxWeight) {
        super();
        this.maxWeight = maxWeight;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getPathTotalWeight() < maxWeight;
    }

}
