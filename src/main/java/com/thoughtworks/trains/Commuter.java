package com.thoughtworks.trains;

import java.util.List;

import com.thoughtworks.trains.exception.NoSuchRouteException;
import com.thoughtworks.trains.graph.Graph;

/**
 * Straight forward Facade for the trains problem. It provides convenience
 * methods to process the inputs to the problem.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 */
public interface Commuter {
    /**
     * Computes the distance (in terms of distance to travel) to go from a
     * starting node to an ending node while visiting a list of intermediate
     * nodes. Note that the link between each city has to be direct. This means
     * that for an input "A-->B-->C", the method should start at A and try to go
     * directly to B, without stopping at any other city first
     * 
     * @param startingCity
     *            The first city in the route
     * @param destinationCity
     *            The last city in the route
     * @param intermediateCities
     *            The intermediate cities between start and destination
     * @return The total distance of the route
     * @throws NoSuchRouteException
     *             if there is not route between the specified cities
     */
    int routeDistance(String startingCity, String destinationCity, List<String> intermediateCities);

    /**
     * Computes the number of different paths existing between two cities with a
     * maximum number of stops between them. Note that this might include
     * cycles. For instance, given the following cities and connections between
     * them "A-->B-->C-->A", there is 2 different paths between A and B with a
     * maximum of 4 stops: "A-->B" and "A-->B-->C-->A-->B".
     * 
     * @param startingCity
     *            The starting city of the trip
     * @param destinationCity
     *            The ending city of the trip
     * @param stops
     *            The maximum number of stops allowed between the two cities
     * @throws NoSuchRouteException
     *             if there is not route between the specified cities
     * @return The number of different paths between the cities
     */
    int numberOfPathsWithMaxStops(String startingCity, String destinationCity, int stops);

    /**
     * Computes the number of different paths existing between two cities with a
     * maximum travel distance between them. Note that this might include
     * cycles. For instance, given the following cities and connections between
     * them "A-5->B-2->C-4->A", there is 2 different paths between A and B with
     * a maximum travel distance of 20: "A-->B" (5) and "A-->B-->C-->A-->B"
     * (16). However, if the maximum travel distance allowed is 15 then only the
     * first path is valid.
     * 
     * @param startingCity
     *            The starting city of the trip
     * @param destinationCity
     *            The ending city of the trip
     * @param weight
     *            The maximum travel distance allowed between the two cities
     * @throws NoSuchRouteException
     *             if there is not route between the specified cities
     * @return The number of different paths between the cities
     */
    int numberOfPathsWithMaxWeight(String startingCity, String destinationCity, int weight);

    /**
     * Computes the number of different paths existing between two cities having
     * an exact number of stops between them.
     * 
     * @param startingCity
     *            The starting city of the trip
     * @param destinationCity
     *            The ending city of the trip
     * @param stops
     *            The exact number of stops that should exist between the two
     *            cities
     * @throws NoSuchRouteException
     *             if there is not route between the specified cities
     * @return The number of different paths between the cities
     */
    int numberOfPathsWithExactStops(String startingCity, String destinationCity, int stops);

    /**
     * Returns the shortest route between two cities in terms of distance to
     * travel. Note that, by definition, the shortest path between two cities
     * can not contain cycles.
     * 
     * @param startingCity
     *            The starting city of the trip
     * @param destinationCity
     *            The ending city of the trip
     * @throws NoSuchRouteException
     *             if there is not route between the specified cities
     * @return The travel distance of the shortest route between the two cities.
     */
    int shortestDistance(String startingCity, String destinationCity);

    /**
     * Returns a {@link Graph} representation of all the cities and connections
     * between them
     * 
     * @return a graph representing all the different routes between the cities
     */
    Graph<String> getAllRoutes();

    int routeDuration(String startingCity, String endCity, List<String> intermediateCities);

}
