package com.thoughtworks.trains.graph;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.trains.exception.NoSuchRouteException;
import com.thoughtworks.trains.filter.MaxHopsPathFilter;
import com.thoughtworks.trains.filter.WeightPathFilter;

public class DirectedGraphTest {
    private Graph<String> graph;

    @Before
    public void initGraph() {
        graph = GraphBuilder.getDefaultGraph();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVertexShouldNotAllowNullValues() {
        graph.addVertex(null);
    }

    @Test
    public void addVertexShouldNotAllowDuplicates() {
        // Adding a new vertex
        assertThat(graph.addVertex("Z")).isTrue();
        assertThat(graph.getAllVertex()).contains("Z");

        // Trying to add an existing vertex should fail
        assertThat(graph.addVertex("Z")).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeShouldFailWhenVertexDoesntExist() {
        graph.addEdge("X", "A", 1);
    }

    @Test
    public void addEdgeShouldUpdateWeightIfDuplicate() {
        graph.addVertex("Z");
        assertThat(graph.addEdge("Z", "A", 5)).isTrue();
        assertThat(graph.getEdge("Z", "A").getWeight()).isEqualTo(5);

        assertThat(graph.addEdge("Z", "A", 15)).isTrue();
        assertThat(graph.getEdge("Z", "A").getWeight()).isEqualTo(15);
    }

    @Test
    public void testGetEdge() {
        graph.addVertex("Z");
        graph.addEdge("A", "Z", 15);
        final Edge<String> azEdge = graph.getEdge("A", "Z");

        assertThat(azEdge.getStartingVertex()).isEqualTo("A");
        assertThat(azEdge.getEndingVertex()).isEqualTo("Z");
        assertThat(azEdge.getWeight()).isEqualTo(15);

        // Should return null when the edge does not exist
        assertThat(graph.getEdge("Z", "B")).isNull();
    }

    @Test
    public void testGetAllEdges() {
        assertThat(graph.getAllVertex()).containsOnly("A", "B", "C", "D", "E");
    }

    @Test(expected = NoSuchRouteException.class)
    public void getAllPathsShouldThrowExceptionWhenNoPathExists() {
        // There is no path between A and B with a cost of less than 3
        graph.getAllPaths("A", "B", new WeightPathFilter<String>(3));
    }

    @Test
    public void testGetAllPaths() {
        // There should be 3 paths between A and D with a max of 3 hops
        // (a,d); (a,b,c,d); (a,d,c,d)
        assertThat(graph.getAllPaths("A", "D", new MaxHopsPathFilter<String>(3))).hasSize(3);

        // But only 1 path with a max of 2 hops: (a,d)
        assertThat(graph.getAllPaths("A", "D", new MaxHopsPathFilter<String>(2))).hasSize(1);
    }

}
