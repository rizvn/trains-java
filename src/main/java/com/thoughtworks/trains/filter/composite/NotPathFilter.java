package com.thoughtworks.trains.filter.composite;

import com.thoughtworks.trains.filter.PathFilter;
import com.thoughtworks.trains.graph.Path;

/**
 * This filter allows to negate the result of other {@link PathFilter}. Using
 * this composite filter we can easily reverse the effect of any given filter
 * without the need to create new ones.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class NotPathFilter<V> implements PathFilter<V> {
    private final PathFilter<V> originalFilter;

    public NotPathFilter(final PathFilter<V> originalFilter) {
        super();
        this.originalFilter = originalFilter;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
        return !originalFilter.passFilter(path);
    }

}
