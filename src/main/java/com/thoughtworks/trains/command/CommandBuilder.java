package com.thoughtworks.trains.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to read through the lines of a given file and instantiate
 * the appropriate {@link Command} instance. Any changes on the input format
 * would only affect this class and not the rest of the application.
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * @see Command
 * @see DistanceCommand
 * @see GraphBuilderCommand
 * @see TripsCommand
 * @see ShortestDistanceCommand
 * 
 */
public class CommandBuilder {
    private static final String DISTANCE_REGEX = "DISTANCE:\\s\\D-\\D(-\\D)*";
    private static final String TRIPS_REGEX = "TRIPS:\\s(MAX_STOPS|EXACT_STOPS|MAX_DISTANCE),(\\d)+,\\D-\\D";
    private static final String GRAPH_REGEX = "GRAPH:\\s(\\D\\D\\d+)(, \\D\\D\\d+)*";
    private static final String SHORTEST_REGEX = "SHORTEST:\\s\\D-\\D";
    private static final String DURATION_REGEX = "DURATION:\\s\\w-\\w(-\\w)*";

    private final PrintStream outputStream;

    public CommandBuilder(final PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Parses the input file reading line by line, ignoring invalid lines. Note
     * that this method doesn't actually execute the commands but only returns
     * them to be executed later on as needed, thus decoupling parsing the file
     * from executing the actions
     * 
     * @param inputFile
     *            The input file
     * @return The list of commands found on the file
     * @throws IOException
     *             If the file can't be found or read
     */
    public List<Command> getCommandsFromFile(final File inputFile) throws IOException {
        final List<Command> commands = new ArrayList<Command>();
        final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        while (reader.ready()) {
            final Command toAdd = processLine(reader);
            if (toAdd != null) {
                commands.add(toAdd);
            }
        }
        reader.close();
        return commands;
    }

    private Command processLine(final BufferedReader reader) throws IOException {
        Command processedCommand = null;
        final String currentLine = reader.readLine().toUpperCase();
        if (currentLine.matches(GRAPH_REGEX)) {
            processedCommand = new GraphBuilderCommand(currentLine);
        } else if (currentLine.matches(SHORTEST_REGEX)) {
            processedCommand = new ShortestDistanceCommand(currentLine, outputStream);
        } else if (currentLine.matches(TRIPS_REGEX)) {
            processedCommand = new TripsCommand(currentLine, outputStream);
        } else if (currentLine.matches(DISTANCE_REGEX)) {
            processedCommand = new DistanceCommand(currentLine, outputStream);
        } else if (currentLine.matches(DURATION_REGEX)) {
            processedCommand = new DurationCommand(currentLine, outputStream);
        } else {
            System.out.println("Line: " + currentLine + " is invalid");
        }
        return processedCommand;
    }

}
