package com.thoughtworks.trains;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.trains.exception.NoSuchRouteException;
import com.thoughtworks.trains.graph.GraphBuilder;

public class CommuterImplTest {
    private final Commuter commuter = new CommuterImpl(GraphBuilder.getDefaultGraph());

    @Test
    public void distanceForABCShouldBe9() {
        assertEquals("The distance for the route A-B-C should be 9", 9,
                commuter.routeDistance("A", "C", getIntermediateList("B")));
    }

    @Test
    public void distanceForADShouldBe5() {
        assertEquals("The distance for the route A-D should be 5", 5, commuter.routeDistance("A", "D", null));
    }

    @Test
    public void distanceForADCShouldBe13() {
        assertEquals("The distance for the route A-D-C should be 13", 13,
                commuter.routeDistance("A", "C", getIntermediateList("D")));
    }

    @Test
    public void distanceForAEBCDShouldBe22() {
        assertEquals("The distance for the route A-B-C should be 22", 22,
                commuter.routeDistance("A", "D", getIntermediateList("E", "B", "C")));
    }

    @Test(expected = NoSuchRouteException.class)
    public void distanceForAEDShouldThrowNoSuchRouteException() {
        commuter.routeDistance("A", "D", getIntermediateList("E"));
    }

    @Test
    public void numberOfTripsBetweenCandCWithMax3StopsShouldBe2() {
        assertEquals("There should be 2 trips between C and C with a maximum of 3 stops", 2,
                commuter.numberOfPathsWithMaxStops("C", "C", 3));
    }

    @Test
    public void numberOfTripsBetweenAandCWith4StopsShouldBe3() {
        assertEquals("There should be 3 trips between A and C with exactly 4 stops", 3,
                commuter.numberOfPathsWithExactStops("A", "C", 4));
    }

    @Test
    public void shortestDistanceFromAtoCShouldBe9() {
        assertEquals("The shortest distance from A to C should be 9", 9, commuter.shortestDistance("A", "C"));
    }

    @Test
    public void shortestDistanceFromBtoBShouldBe9() {
        assertEquals("The shortest distance from B to B should be 9", 9, commuter.shortestDistance("B", "B"));
    }

    @Test
    public void numberOfRoutesBetweenCandCshouldBe7() {
        assertEquals("There should be 7 routes between C and C with a distance less than 30", 7,
                commuter.numberOfPathsWithMaxWeight("C", "C", 30));
    }
    
    @Test
    public void durationOfTheTripABCShouldBe11() throws Exception {
        assertEquals(11, commuter.routeDuration("A", "C", getIntermediateList("B")));
    }

    private List<String> getIntermediateList(final String... nodes) {
        final List<String> intermediateList = new ArrayList<String>();
        for (final String each : nodes) {
            intermediateList.add(each);
        }
        return intermediateList;
    }
}
