package com.thoughtworks.trains.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class WeightPathFilterTest {
    private final PathFilter<String> filter = new WeightPathFilter<String>(15);

    @Test
    public void testPassFilter() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 2));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 3));

        // The weight of the path is 10 and the max weight on the filter is 15
        // So the filter should pass
        assertThat(filter.passFilter(targetPath)).isTrue();

        // When the weight of the path is equal or greater to the filter, it
        // should fail
        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "A", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();

        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "E", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

}
