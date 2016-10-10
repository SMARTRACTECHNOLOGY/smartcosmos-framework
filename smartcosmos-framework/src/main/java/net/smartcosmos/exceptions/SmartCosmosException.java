package net.smartcosmos.exceptions;

/**
 * Generic base exception for the SMART COSMOS Objects platform.
 */
public class SmartCosmosException extends RuntimeException {

    private static final long serialVersionUID = 8059819376742981420L;

    /**
     * Default Exception constructor.
     *
     * @param message the message in the exception
     */
    public SmartCosmosException(String message) {

        super(message);
    }

    /**
     * Default Exception constructor.
     *
     * @param message the message in the exception
     * @param cause the wrapped, originating exception
     */
    public SmartCosmosException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Default Exception constructor.
     *
     * @param cause the wrapped, originating exception
     */
    public SmartCosmosException(Throwable cause) {

        super(cause);
    }
}
