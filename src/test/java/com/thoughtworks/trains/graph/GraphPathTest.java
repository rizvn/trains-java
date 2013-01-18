package com.thoughtworks.trains.graph;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GraphPathTest {
    private Path<String> path;
    private final Edge<String> abEdge = DefaultEdge.getWeightedEdge("A", "B", 5);
    private final Edge<String> bcEdge = DefaultEdge.getWeightedEdge("B", "C", 15);
    private final Edge<String> cdEdge = DefaultEdge.getWeightedEdge("C", "D", 5);

    @Before
    public void initPath() {
        path = GraphPath.emptyPath();
        path.addEdge(abEdge);
        path.addEdge(bcEdge);
        path.addEdge(cdEdge);
    }

    @Test
    public void addEdgeShouldIncreaseWeightAndHops() {
        final Edge<String> newEdge = DefaultEdge.getWeightedEdge("D", "E", 5);
        path.addEdge(newEdge);

        assertThat(path.getPathTotalWeight()).isEqualTo(30);
        assertThat(path.getNumberOfHops()).isEqualTo(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeShouldFailWhenEdgeIsNotConsecutive() {
        // The last edge on the path is "C-->D" so adding "E-->A" should fail
        final Edge<String> nonConsecutiveEdge = DefaultEdge.getWeightedEdge("E", "A", 1);
        path.addEdge(nonConsecutiveEdge);
    }

    @Test
    public void getPathTotalWeightShouldEqualEdgesSum() {
        assertThat(path.getPathTotalWeight()).isEqualTo(25);
    }

    @Test
    public void getNumberOfHopsShouldEqualEdgeNumber() {
        assertThat(path.getNumberOfHops()).isEqualTo(3);
    }

    @Test
    public void getLastNodeShouldReturnLastEdgeEndingVertex() {
        assertThat(path.getLastNode()).isEqualTo("D");
    }

    @Test
    public void getLastNodeWhenPathIsEmptyShouldReturnNull() {
        path = GraphPath.emptyPath();
        assertThat(path.getLastNode()).isNull();
    }

    @Test
    public void removeLastEdgeShouldDecreaseWeightAndHops() {
        path.removeLastEdge();

        assertThat(path.getPathTotalWeight()).isEqualTo(20);
        assertThat(path.getNumberOfHops()).isEqualTo(2);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetEdgeList() {
        final List<Edge<String>> edgeList = path.getEdgeList();
        assertThat(edgeList).containsExactly(abEdge, bcEdge, cdEdge);
    }

    @Test
    public void testHasRepeatedEdges() {
        // The initial path does not contain repeated edges
        assertThat(path.hasRepeatedEdges()).isFalse();

        // After adding an existing edge it should return true
        final Edge<String> daEdge = DefaultEdge.getWeightedEdge("D", "A", 5);
        path.addEdge(daEdge);
        path.addEdge(abEdge);
        assertThat(path.hasRepeatedEdges()).isTrue();
    }

    @Test
    public void testCompareTo() {
        // Should return 0 for paths with the same weight
        final Path<String> otherPath = GraphPath.copyPath(path);
        assertThat(otherPath.compareTo(path)).isZero();

        // Now other path has a greater total weight
        final Edge<String> daEdge = DefaultEdge.getWeightedEdge("D", "A", 5);
        otherPath.addEdge(daEdge);
        assertThat(otherPath.compareTo(path)).isPositive();
        assertThat(path.compareTo(otherPath)).isNegative();

    }

}
