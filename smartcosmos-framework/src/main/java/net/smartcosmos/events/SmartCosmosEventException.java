package net.smartcosmos.events;

import net.smartcosmos.exceptions.SmartCosmosException;

/**
 * Event handler exception for the SMART COSMOS Objects platform, based on {@see SmartCosmosException}.
 */
public class SmartCosmosEventException extends SmartCosmosException {
    public SmartCosmosEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
