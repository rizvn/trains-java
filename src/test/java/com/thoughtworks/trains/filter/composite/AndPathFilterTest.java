package com.thoughtworks.trains.filter.composite;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.trains.filter.ExactHopsPathFilter;
import com.thoughtworks.trains.filter.PathFilter;
import com.thoughtworks.trains.filter.WeightPathFilter;
import com.thoughtworks.trains.graph.DefaultEdge;
import com.thoughtworks.trains.graph.GraphPath;
import com.thoughtworks.trains.graph.Path;

public class AndPathFilterTest {
    private final PathFilter<String> filter = new AndPathFilter<String>(new ExactHopsPathFilter<String>(3),
            new WeightPathFilter<String>(15));
    private Path<String> targetPath;

    @Before
    public void initPath() {
        targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 2));
    }

    @Test
    public void shouldPassWhenBothFiltersPass() {
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 3));
        // The filter checks to see if the path has exactly 3 hops and its
        // weight is less than 15, so the previous path should pass
        assertThat(filter.passFilter(targetPath)).isTrue();
    }

    @Test
    public void shouldFailWhenFirstFilterFails() {
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 3));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "E", 3));

        // targetPath has 4 hops now and a total weight of 13, so
        // the first filter return false and the second one true
        // The and filter should therefore fail
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

    @Test
    public void shouldFailWhenSecondFilterFails() {
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 13));

        // targetPath has 3 hops now and a total weight of 20, so
        // the first filter return true and the second one false
        // The and filter should therefore fail
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

}
