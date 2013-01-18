package com.thoughtworks.trains.filter;

import com.thoughtworks.trains.graph.Path;

/**
 * This filter will check that the path passed in its constructor starts with
 * the path passed as parameter to {@link #passFilter(Path)}. So for instance,
 * if the objective path is "A-->B-->C" and the passed path is "A--B" the filter
 * will return true. However, if the passed path is anything other than "A-->B"
 * the filter will return false
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 * @param <V>
 *            The type of the nodes in the path
 */
public class ContainsPathFilter<V> implements PathFilter<V> {
    private final Path<V> objectivePath;

    public ContainsPathFilter(final Path<V> objectivePath) {
        this.objectivePath = objectivePath;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
        return objectivePath.startsWith(path);
    }

}
