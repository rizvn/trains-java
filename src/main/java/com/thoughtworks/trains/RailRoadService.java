package com.thoughtworks.trains;

import java.io.File;
import java.util.List;

import com.thoughtworks.trains.command.Command;
import com.thoughtworks.trains.command.CommandBuilder;
import com.thoughtworks.trains.graph.GraphBuilder;

public class RailRoadService {

    public static void main(final String[] args) throws Exception {
        final Commuter commuter = new CommuterImpl(GraphBuilder.getEmptyGraph());
        File inputFile = null;
        if (args.length == 1) {
            inputFile = new File(args[0]);
        } else {
            inputFile = new File(RailRoadService.class.getResource("/input.txt").toURI());
        }

        final List<Command> inputCommands = new CommandBuilder(System.out).getCommandsFromFile(inputFile);
        for (final Command eachCommand : inputCommands) {
            eachCommand.execute(commuter);
        }
    }

}
