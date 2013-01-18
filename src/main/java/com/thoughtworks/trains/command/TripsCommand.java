package com.thoughtworks.trains.command;

import java.io.PrintStream;

import com.thoughtworks.trains.Commuter;

/**
 * Command implementation to a trip input. This command takes a modifier that
 * has 3 possible values: MAX_STOPS, EXACT_STOPS and MAX_DISTANCE. Therefore,
 * valid lines for each case would be:<br/>
 * <b>Trips: MAX_STOPS,3,C-C<br/>
 * Trips: EXACT_STOPS,4,A-C<br/>
 * Trips: MAX_DISTANCE,30,C-C<br/>
 * </b> Note that this class is package private to prevent it from being
 * instantiated from outside. Instances of this class are only created in
 * {@link CommandBuilder}. This requires the client to refer to the returned
 * object by its interface rather than its implementation class, which is
 * generally good practice
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * @see DistanceCommand
 * @see ShortestDistanceCommand
 * @see GraphBuilderCommand
 * 
 */
class TripsCommand extends AbstractCommand {
    // Injected on the constructor to avoid hard-coding "System.out"
    private final PrintStream outputStream;

    public TripsCommand(final String commandLine, final PrintStream stream) {
        super(commandLine);
        this.outputStream = stream;
    }

    @Override
    public void execute(final Commuter commuter) {
        final String routeLine = getCommandLine().substring(7);
        final String[] commandParts = routeLine.split(",");

        final String filterCriteria = commandParts[0];
        final int filterValue = Integer.valueOf(commandParts[1]);
        final String startNode = String.valueOf(commandParts[2].charAt(0));
        final String endNode = String.valueOf(commandParts[2].charAt(2));

        int numberOfTrips = 0;

        try {
            if (filterCriteria.equalsIgnoreCase("MAX_STOPS")) {
                numberOfTrips = commuter.numberOfPathsWithMaxStops(startNode, endNode, filterValue);
            } else if (filterCriteria.equalsIgnoreCase("EXACT_STOPS")) {
                numberOfTrips = commuter.numberOfPathsWithExactStops(startNode, endNode, filterValue);
            } else if (filterCriteria.equalsIgnoreCase("MAX_DISTANCE")) {
                numberOfTrips = commuter.numberOfPathsWithMaxWeight(startNode, endNode, filterValue);
            }
            outputStream.println(numberOfTrips);
        } catch (final Exception e) {
            outputStream.println(RouteCommand.NO_ROUTE_MSG);
        }

    }

}
