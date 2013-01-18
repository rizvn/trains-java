package com.thoughtworks.trains.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.trains.Commuter;
import com.thoughtworks.trains.exception.NoSuchRouteException;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCommandTest {
    // Mock the commuter to later verify that it was called
    // with the appropriate parameters
    @Mock
    private Commuter commuter;
    // Same with the PrintStream
    @Mock
    private PrintStream outputStream;
    private Command distanceCommand;

    @Before
    public void intCommand() {
        distanceCommand = new DistanceCommand("Distance: A-B-C", outputStream);
    }

    @Test
    public void shouldInvokeRouteDistance() {
        final List<String> intermediateNodes = new ArrayList<String>();
        intermediateNodes.add("B");
        // Set the commuter to return a given value when called with the
        // appropriate parameters to then verify that the same value was
        // printed to the stream
        final int routeDistance = 5;
        when(commuter.routeDistance("A", "C", intermediateNodes)).thenReturn(routeDistance);

        distanceCommand.execute(commuter);

        verify(outputStream).println(routeDistance);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldPrintNoRouteWhenExceptionIsThrown() {
        final List<String> intermediateNodes = new ArrayList<String>();
        intermediateNodes.add("B");
        when(commuter.routeDistance("A", "C", intermediateNodes)).thenThrow(NoSuchRouteException.class);

        distanceCommand.execute(commuter);

        verify(outputStream).println(RouteCommand.NO_ROUTE_MSG);
    }

}
