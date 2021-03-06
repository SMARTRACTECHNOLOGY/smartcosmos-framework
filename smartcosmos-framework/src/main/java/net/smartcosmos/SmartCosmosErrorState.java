package net.smartcosmos;

/**
 * An enum of the error states that may be returned from SmartCosmos services.
 */
public enum SmartCosmosErrorState {
    SUCCESS(1, "Success"),
    FAILURE(0, "Failure"),
    NOT_AUTHORIZED(-1, "Authenticated user is not authorized to make this call"),
    NOT_FOUND(-2, "No such URN"),
    NOT_LICENSED(-3, "Unlicensed feature"),
    UNKNOWN_ENTITY_TYPE(-4, "Unknown Entity Reference Type"),
    MISSING_REQUIRED_FIELD(-5, "JSON is missing a required field: %s"),
    DEVICE_NOT_FOUND(-6, "No device found with identification %s"),
    DUPLICATE_FOUND(-7, "A %s with matching key %s already exists"),
    NO_MATCH(-8, "No matching record with %s of %s exists"),
    ENTITY_NOT_FOUND(-9, "Unknown %s entity with urn %s"),
    TYPE_NOT_DEFINED(-10, "%s enum does not define type %s"),
    SESSION_CLOSED(-11, "session with URN %s was previously closed"),
    USER_NOT_FOUND(-12, "No user associated with email address %s"),
    FILE_PENDING_UPLOAD(-13, "File URN %s exists but is flagged as pending content upload"),
    AUTHENTICATION_REQUIRED(-14, "Endpoint requires authentication"),
    VALIDATION_FAILURE(-15, "Input Validation Failure"),
    EMPTY_REQUEST_BODY(-16, "Request body must not be empty"),
    PARSE_FAILURE(-17, "Unable to parse input"),
    UNSUPPORTED_ENTITY_TYPE(-18, "Unsupported Entity Reference Type"),
    INVALID_EXTENSION_OPERATION(-50, "Extensions are not permitted to perform %s"),
    AUTHORIZATION_FAILURE(-51, "Caller lacked the authorization to complete the requested operation"),
    INTERNAL_ERROR(-500, "Internal Server Error");

    private int code;
    private String description;

    private SmartCosmosErrorState(int theCode, String theDescription) {
        code = theCode;
        description = theDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription(String...replacements) {
        return String.format(description, replacements);
    }

    public int getCode() {
        return code;
    }
}
