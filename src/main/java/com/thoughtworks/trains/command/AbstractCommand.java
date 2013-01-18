package com.thoughtworks.trains.command;

/**
 * Base implementation of {@link Command}. It abstracts away the string line
 * containing each command and the {@link #equals(Object)} and
 * {@link #hashCode()} implementation. It is worth noting that all the concrete
 * Command classes are only instantiated by {@link CommandBuilder} and this
 * class is responsible to parse and validate every line from the input file.
 * Therefore, there is no need to validate the commandLine format on each
 * Command class.
 * 
 * @see Command
 * @see DistanceCommand
 * @see GraphBuilderCommand
 * @see TripsCommand
 * @see ShortestDistanceCommand
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 */
abstract class AbstractCommand implements Command {
    private final String commandLine;

    public AbstractCommand(final String commandLine) {
        this.commandLine = commandLine;
    }

    protected final String getCommandLine() {
        return commandLine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (commandLine == null ? 0 : commandLine.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractCommand other = (AbstractCommand) obj;
        if (commandLine == null) {
            if (other.commandLine != null) {
                return false;
            }
        } else if (!commandLine.equals(other.commandLine)) {
            return false;
        }
        return true;
    }

}
