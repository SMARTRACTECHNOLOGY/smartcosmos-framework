package net.smartcosmos.exceptions;

/**
 * @author voor
 */
public class NoEntityFoundException extends ServiceException {

	public NoEntityFoundException(String message) {
		super(message);
	}

	public NoEntityFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
