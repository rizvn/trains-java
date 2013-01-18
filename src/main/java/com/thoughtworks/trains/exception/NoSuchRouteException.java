package com.thoughtworks.trains.exception;

/**
 * Unchecked exception thrown to indicate that there is no path between two
 * vertex of a graph
 * 
 * @author "Jose Luis Ordiales Coscia <jlordiales@gmail.com>"
 * 
 */
public class NoSuchRouteException extends RuntimeException {
    private static final long serialVersionUID = 888412587590187957L;

    // Instead of just using the default constructor receiving the message of
    // the exception
    // we receive the starting and ending vertex to ensure that the message is
    // consistent
    // regardless of where this exception is thrown from.
    public NoSuchRouteException(final String startingVertex, final String endingVertex) {
        super("No route exists between " + startingVertex + " and " + endingVertex);
    }
}
