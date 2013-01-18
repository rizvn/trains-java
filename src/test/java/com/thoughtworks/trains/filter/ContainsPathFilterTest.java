package com.thoughtworks.trains.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class ContainsPathFilterTest {
    private PathFilter<String> filter;
    private Path<String> targetPath;

    @Before
    public void setUp() throws Exception {
        targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));

        filter = new ContainsPathFilter<String>(targetPath);

    }

    @Test
    public void testPassFilter() {
        // The target path starts with (A,B) so a path with only (A,B) should
        // pass
        final Path<String> partialPath = GraphPath.emptyPath();
        partialPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        assertThat(filter.passFilter(partialPath)).isTrue();

        // The second edge on the current path is different from the target, so
        // it should fail now
        partialPath.addEdge(DefaultEdge.getWeightedEdge("B", "D", 5));
        assertThat(filter.passFilter(partialPath)).isFalse();

        // If the two paths are equal the filter should pass
        assertThat(filter.passFilter(GraphPath.copyPath(targetPath))).isTrue();
    }

}
