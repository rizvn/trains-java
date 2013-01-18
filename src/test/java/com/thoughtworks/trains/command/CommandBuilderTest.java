package com.thoughtworks.trains.command;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandBuilderTest {
    @Mock
    // We don't really care in this test about the interactions
    // with the PrintStream so we can just provide an empty mock
    private PrintStream outputStream;
    private CommandBuilder builder;

    @Before
    public void initCommandBuilder() {
        builder = new CommandBuilder(outputStream);
    }

    @Test
    public void shouldReadEveryCommandFromFile() throws URISyntaxException, IOException {
        final File inputFile = new File(getClass().getResource("/graphInputTest01.txt").toURI());

        assertThat(builder.getCommandsFromFile(inputFile)).isEqualTo(getExpectedCommands());
    }

    private List<Command> getExpectedCommands() {
        final List<Command> expectedCommands = new ArrayList<Command>();
        expectedCommands.add(new GraphBuilderCommand("GRAPH: AB15, BC4, CD8, DC8, DE6, AD5, CE12, EB3, AE7"));
        expectedCommands.add(new DistanceCommand("DISTANCE: A-B-C", outputStream));
        expectedCommands.add(new TripsCommand("TRIPS: MAX_STOPS,3,C-C", outputStream));
        expectedCommands.add(new TripsCommand("TRIPS: EXACT_STOPS,4,A-C", outputStream));
        expectedCommands.add(new ShortestDistanceCommand("SHORTEST: B-B", outputStream));
        expectedCommands.add(new TripsCommand("TRIPS: MAX_DISTANCE,30,C-C", outputStream));
        expectedCommands.add(new DurationCommand("DURATION: A-B-C", outputStream));
        expectedCommands.add(new DurationCommand("DURATION: A-B-C-D", outputStream));
        return expectedCommands;
    }

}
