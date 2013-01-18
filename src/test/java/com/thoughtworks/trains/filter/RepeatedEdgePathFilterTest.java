package com.thoughtworks.trains.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class RepeatedEdgePathFilterTest {
    private final PathFilter<String> filter = new RepeatedEdgePathFilter<String>();

    @Test
    public void shouldPassWhenPathHasNoRepeatedEdges() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        assertThat(filter.passFilter(targetPath)).isTrue();
    }

    @Test
    public void shouldFailWhenPathHasRepeatedEdges() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "A", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

}
