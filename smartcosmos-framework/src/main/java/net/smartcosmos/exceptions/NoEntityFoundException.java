package net.smartcosmos.exceptions;

import lombok.Getter;

/**
 * @author voor
 */
public class NoEntityFoundException extends ServiceException {

    protected static final Integer ERR_FAILURE = 0;
    protected static final Integer ERR_RECORD_NOT_EXISTS = -8;
    protected static final Integer ERR_UNKNOWN_ENTITY = -9;

    @Getter
    private Integer code = ERR_FAILURE;

    public NoEntityFoundException(String message) {
        super(message);
    }

    public NoEntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEntityFoundException(String entityType, String field, String id) {
        super(String.format("No matching %s record with %s of %s exists", entityType, field, id));

        if (!"urn".equalsIgnoreCase(field)) {
            code = ERR_RECORD_NOT_EXISTS;
        } else {
            code = ERR_UNKNOWN_ENTITY;
        }
    }

    public NoEntityFoundException(String entityType, String id) {
        super(String.format("Unknown %s entity with urn %s", entityType, id));
        code = ERR_UNKNOWN_ENTITY;
    }
}
