package org.cyclop.model;

/**
 * @author Maciej Miklas
 */
public final class QueryException extends ServiceException {
    public QueryException(String message) {
        super(message);
    }

    public QueryException(String message, Exception cause) {
        super(message, cause);
    }
}
