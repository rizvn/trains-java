package com.thoughtworks.trains.graph;

public class GraphBuilder {

    public static Graph<String> getEmptyGraph() {
        return new DirectedGraph<String>();
    }

    public static Graph<String> getDefaultGraph() {
        final Graph<String> routeGraph = new DirectedGraph<String>();
        final String nodeA = "A";
        final String nodeB = "B";
        final String nodeC = "C";
        final String nodeD = "D";
        final String nodeE = "E";

        routeGraph.addVertex(nodeA);
        routeGraph.addVertex(nodeB);
        routeGraph.addVertex(nodeC);
        routeGraph.addVertex(nodeD);
        routeGraph.addVertex(nodeE);

        routeGraph.addEdge(nodeA, nodeB, 5);
        routeGraph.addEdge(nodeB, nodeC, 4);
        routeGraph.addEdge(nodeC, nodeD, 8);
        routeGraph.addEdge(nodeD, nodeC, 8);
        routeGraph.addEdge(nodeD, nodeE, 6);
        routeGraph.addEdge(nodeA, nodeD, 5);
        routeGraph.addEdge(nodeC, nodeE, 2);
        routeGraph.addEdge(nodeE, nodeB, 3);
        routeGraph.addEdge(nodeA, nodeE, 7);
        return routeGraph;
    }
}
