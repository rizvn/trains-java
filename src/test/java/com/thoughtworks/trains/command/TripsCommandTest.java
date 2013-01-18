package com.thoughtworks.trains.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.trains.Commuter;

@RunWith(MockitoJUnitRunner.class)
public class TripsCommandTest {
    private Command command;
    @Mock
    private Commuter commuter;
    @Mock
    private PrintStream stream;

    @Test
    public void shouldInvokeMaxStops() {
        command = new TripsCommand("Trips: MAX_STOPS,3,C-C", stream);
        final int stops = 5;
        when(commuter.numberOfPathsWithMaxStops("C", "C", 3)).thenReturn(stops);

        command.execute(commuter);

        verify(stream).println(stops);
    }

    @Test
    public void shouldInvokeMaxWeight() {
        command = new TripsCommand("Trips: MAX_DISTANCE,30,B-C", stream);
        final int weight = 5;
        when(commuter.numberOfPathsWithMaxWeight("B", "C", 30)).thenReturn(weight);

        command.execute(commuter);

        verify(stream).println(weight);
    }

    @Test
    public void shouldInvokeExactStops() {
        command = new TripsCommand("Trips: EXACT_STOPS,4,A-C", stream);
        final int stops = 5;
        when(commuter.numberOfPathsWithExactStops("A", "C", 4)).thenReturn(stops);

        command.execute(commuter);

        verify(stream).println(stops);
    }

}
