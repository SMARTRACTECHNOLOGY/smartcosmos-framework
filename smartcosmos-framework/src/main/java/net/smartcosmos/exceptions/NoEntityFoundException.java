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

    /**
     * This version of NoEntityFoundException produces an error message like
     * <code>
     *     {"code": -8, "message": "No matching Object record with objectUrn of urn:my-object-urn exists"}
     * </code>
     *
     * @param entityType the type of the entity, e.g., Object or Metadata
     * @param fieldName the queried field, e.g., objectUrn
     * @param fieldValue the field value
     */
    public NoEntityFoundException(String entityType, String fieldName, String fieldValue) {
        super(String.format("No matching %s record with %s of %s exists", entityType, fieldName, fieldValue));

        if (!"urn".equalsIgnoreCase(fieldName)) {
            code = ERR_RECORD_NOT_EXISTS;
        } else {
            code = ERR_UNKNOWN_ENTITY;
        }
    }

    /**
     * This version of NoEntityFoundException produces an error message like
     * <code>
     *     {"code": -9, "message": "Unknown Object entity with urn urn:uuid:71b81766-afb2-4be3-943f-6a24b6ae15cc"}
     * </code>
     *
     * @param entityType the type of the entity, e.g., Object or Metadata
     * @param id the entity identifier, e.g. the URN
     */
    public NoEntityFoundException(String entityType, String id) {
        super(String.format("Unknown %s entity with urn %s", entityType, id));
        code = ERR_UNKNOWN_ENTITY;
    }
}
