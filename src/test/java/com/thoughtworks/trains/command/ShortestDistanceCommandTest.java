package com.thoughtworks.trains.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.trains.Commuter;
import com.thoughtworks.trains.exception.NoSuchRouteException;

@RunWith(MockitoJUnitRunner.class)
public class ShortestDistanceCommandTest {
    @Mock
    private Commuter commuter;
    @Mock
    private PrintStream stream;
    private Command command;

    @Before
    public void initCommand() {
        command = new ShortestDistanceCommand("Shortest: A-E", stream);
    }

    @Test
    public void shouldInvokeShortestDistance() {
        final int distance = 5;
        when(commuter.shortestDistance("A", "E")).thenReturn(distance);

        command.execute(commuter);

        verify(stream).println(distance);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldPrintNoRouteWhenExceptionIsThrown() {
        when(commuter.shortestDistance("A", "E")).thenThrow(NoSuchRouteException.class);

        command.execute(commuter);

        verify(stream).println(RouteCommand.NO_ROUTE_MSG);
    }

}
