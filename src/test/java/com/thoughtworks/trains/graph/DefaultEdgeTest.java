package com.thoughtworks.trains.graph;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class DefaultEdgeTest {
    private final Edge<String> edge = DefaultEdge.getWeightedEdge("A", "B", 15);

    @Test
    public void equalShouldNotConsiderWeight() {
        final Edge<String> edgeWithDifferentWeight = DefaultEdge.getWeightedEdge("A", "B", 5);
        assertThat(edgeWithDifferentWeight.equals(edge)).isTrue();

        final Edge<String> differentEdge = DefaultEdge.getWeightedEdge("B", "C", 5);
        assertThat(differentEdge.equals(edge)).isFalse();
    }

}
