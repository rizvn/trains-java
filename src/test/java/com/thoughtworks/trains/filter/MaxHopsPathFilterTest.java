package com.thoughtworks.trains.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class MaxHopsPathFilterTest {
    private final PathFilter<String> filter = new MaxHopsPathFilter<String>(3);

    @Test
    public void shouldPassWhenPathIsEmpty() {
        final Path<String> path = GraphPath.emptyPath();
        assertThat(filter.passFilter(path)).isTrue();
    }

    @Test
    public void testPassFilter() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        // The filter looks for a max of 3 hops so the previous path should pass
        assertThat(filter.passFilter(targetPath)).isTrue();

        // It should also pass when the number of hops in the path is equal to
        // the max set in the filter
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        assertThat(filter.passFilter(targetPath)).isTrue();

        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "E", 5));
        // The path has 4 hops now so the filter should fail
        assertThat(filter.passFilter(targetPath)).isFalse();

    }

}
