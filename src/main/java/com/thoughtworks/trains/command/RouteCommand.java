package com.thoughtworks.trains.command;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.trains.Commuter;
import com.thoughtworks.trains.exception.NoSuchRouteException;

public abstract class RouteCommand extends AbstractCommand {

    protected static final String NO_ROUTE_MSG = "NO SUCH ROUTE";
    protected final PrintStream outputStream;

    public RouteCommand(String commandLine, PrintStream outputStream) {
        super(commandLine);
        this.outputStream = outputStream;
    }
    
    protected abstract int callCommuter(Commuter commuter,String start, String end, List<String> intermediate);

    @Override
    public void execute(final Commuter commuter) {
        final String routeLine = getCommandLine().substring(10);
        final String[] nodes = routeLine.split("-");
        try {
            outputStream.println(callCommuter(commuter,nodes[0], nodes[nodes.length - 1], getIntermediateList(nodes)));
        } catch (final NoSuchRouteException e) {
            outputStream.println(NO_ROUTE_MSG);
        }
    }

    private List<String> getIntermediateList(final String[] nodes) {
        final List<String> intermediateList = new ArrayList<String>();
        for (int i = 1; i < nodes.length - 1; i++) {
            intermediateList.add(nodes[i]);
        }
        return intermediateList;
    }

}
