package net.smartcosmos.exceptions;

/**
 * Generic base exception for the SMART COSMOS Objects platform.
 */
public class SmartCosmosException extends Exception {

    private static final long serialVersionUID = -4863318680106697788L;

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
