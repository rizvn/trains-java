package com.thoughtworks.trains.command;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.trains.Commuter;
import com.thoughtworks.trains.graph.Graph;

@RunWith(MockitoJUnitRunner.class)
public class GraphBuilderCommandTest {
    @Mock
    private Graph<String> graph;
    @Mock
    private Commuter commuter;

    @Test
    public void shouldInvokeCommuterGraph() {
        final Command command = new GraphBuilderCommand("Graph: AB3, BC15");
        when(commuter.getAllRoutes()).thenReturn(graph);

        command.execute(commuter);

        verify(commuter, atLeastOnce()).getAllRoutes();

        verify(graph, times(1)).addVertex("A");
        // It tries to add B twice because it appears twice on the input line
        // The second invocation has no effect on the graph
        verify(graph, times(2)).addVertex("B");
        verify(graph, times(1)).addVertex("C");

        verify(graph).addEdge("A", "B", 3);
        verify(graph).addEdge("B", "C", 15);
    }

}
