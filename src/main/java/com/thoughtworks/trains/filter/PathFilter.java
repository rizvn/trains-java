package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.filter.composite.AndPathFilter;
import com.thoughtworks.trains.filter.composite.NotPathFilter;
import com.thoughtworks.trains.filter.composite.OrPathFilter;
import com.thoughtworks.trains.graph.Path;

/**
 * Base interface to filter paths given different criteria. Each concrete
 * implementation can decide when the given path is valid or not. The filters in
 * this package use the Composite design pattern to easily extend and combine an
 * arbitrary number of filters.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 * @see AndPathFilter
 * @see OrPathFilter
 * @see NotPathFilter
 */
public interface PathFilter<V> {
    boolean passFilter(final Path<V> path);

}
