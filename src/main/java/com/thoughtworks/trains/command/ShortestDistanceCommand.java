package com.thoughtworks.trains.command;

import java.io.PrintStream;

import com.thoughtworks.trains.Commuter;
import com.thoughtworks.trains.exception.NoSuchRouteException;

/**
 * Command implementation to a shortest distance input. A valid line example for
 * this command would be:<br/>
 * <b>Shortest: A-B</b><br/>
 * where A and B represent the starting and ending vertex, respectively. The
 * upper and lower limit for the number of vertex on the specified path is 2.
 * Vertex are assumed to be single letters. Note that this class is package
 * private to prevent it from being instantiated from outside. Instances of this
 * class are only created in {@link CommandBuilder}. This requires the client to
 * refer to the returned object by its interface rather than its implementation
 * class, which is generally good practice
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * @see DistanceCommand
 * @see TripsCommand
 * @see GraphBuilderCommand
 * 
 */
class ShortestDistanceCommand extends AbstractCommand {
    // Injected on the constructor to avoid hard-coding "System.out"
    private final PrintStream outputStream;

    public ShortestDistanceCommand(final String commandLine, final PrintStream stream) {
        super(commandLine);
        this.outputStream = stream;
    }

    @Override
    public void execute(final Commuter commuter) {
        final String routeLine = getCommandLine().substring(10);
        final String from = String.valueOf(routeLine.charAt(0));
        final String to = String.valueOf(routeLine.charAt(2));
        try {
            outputStream.println(commuter.shortestDistance(from, to));
        } catch (final NoSuchRouteException e) {
            outputStream.println(RouteCommand.NO_ROUTE_MSG);
        }
    }
}
