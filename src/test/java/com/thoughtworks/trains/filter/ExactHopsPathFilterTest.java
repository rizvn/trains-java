package com.thoughtworks.trains.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class ExactHopsPathFilterTest {
    private final PathFilter<String> filter = new ExactHopsPathFilter<String>(3);

    @Test
    public void testPassFilter() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        // The filter looks for exactly 3 hops so the previous path should pass
        assertThat(filter.passFilter(targetPath)).isTrue();

        targetPath.removeLastEdge();
        // The path has 2 edges now so the filter should fail
        assertThat(filter.passFilter(targetPath)).isFalse();

    }

}
